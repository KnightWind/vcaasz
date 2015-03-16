package com.bizconf.vcaasz.action.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.Billing;
import com.bizconf.vcaasz.entity.ConfBilling;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.ServiceItemBilling;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.UserBilling;
import com.bizconf.vcaasz.service.BillingService;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

/**
 * @desc 企业管理员账单
 * @author Darren
 * @date 2014-11-3
 */
@ReqPath("billing")
public class BillingController extends BaseController {

	private final Logger logger = Logger.getLogger(BillingController.class);
	@Autowired
	UserService userService;
	@Autowired
	ConfService confService;
	@Autowired
	SiteService siteService;
	@Autowired
	BillingService billingService;
	
	/**
	 * 列举站点的总账单
	 * */
	@AsController(path = "listTotalBill")
	public Object listBilling(@CParam("year") String yearStr,@CParam("month") String monthStr, 
			@CParam("pageNo") Integer pageNo,HttpServletRequest request){
		
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		if(currentUser == null){
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_NO_ADMIN);
			return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
		}
		SiteBase site = siteService.getSiteBaseById(currentUser.getSiteId());
		if(site == null){
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_NO_SITE);
			return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
//			calendar.setTime(sdf.parse("2015-01-02 12:10:10"));
			Date startDate = null;
			Date endDate = null;
			//如果时间是null，默认设置上个月时间
			if(yearStr == null || "".equals(yearStr) || monthStr == null || "".equals(monthStr)){
				int date = calendar.get(Calendar.DATE);//日期
				int hours = calendar.get(Calendar.HOUR_OF_DAY);//时间
				//每月5日10点之后
				if(date == ConstantUtil.BILL_DATE && hours >= ConstantUtil.BILL_HOUR){
					calendar.add(Calendar.MONTH, -1);
				}else if(date < ConstantUtil.BILL_DATE){
					calendar.add(Calendar.MONTH, -2);
				}else if(date > ConstantUtil.BILL_DATE){
					calendar.add(Calendar.MONTH, -1);
				}else {
					calendar.add(Calendar.MONTH, -2);
				}
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				
				String start = year +"-"+ (month+1) +"-"+ minDay +" "+ "00:00:00";
				String end = year +"-"+ (month+1) +"-"+ maxDay +" "+ "23:59:59";
				startDate = sdf.parse(start);
				endDate = sdf.parse(end);
			}else{
				calendar.set(Calendar.YEAR, Integer.parseInt(yearStr));
				calendar.set(Calendar.MONTH, Integer.parseInt(monthStr) - 1);
				int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				String start = yearStr +"-"+ monthStr +"-"+ minDay +" 00:00:00";
				String end = yearStr +"-"+ monthStr +"-"+ maxDay + " 23:59:59";
				startDate = sdf.parse(start);
				endDate = sdf.parse(end);
			}
			//获取该站点的总账单
			List<Billing> billingBean = billingService.getUserBillingPage(pageNo, 0, null,site, startDate, endDate, null);
			//查询当前站点的总金额
			Double sumCharge = billingService.getUserTotalTelFee(null,site, startDate, endDate,null);
			//组织站点的总账单
			List<ServiceItemBilling> itemsBill = null;
			if(billingBean ==null || billingBean.isEmpty()){
				itemsBill = new ArrayList<ServiceItemBilling>();
			}else{
				//组织站点的总账单
				itemsBill = billingService.setSiteTotalBilling(billingBean,site,startDate,endDate);
			}
			
			Calendar currentCalendar = Calendar.getInstance();
			currentCalendar.setTime(new Date());
//			currentCalendar.setTime(sdf.parse("2015-01-02 12:10:10"));
			request.setAttribute("curryear", currentCalendar.get(Calendar.YEAR));
			request.setAttribute("currmonth", currentCalendar.get(Calendar.MONTH)+1);
			request.setAttribute("sumCharge", sumCharge);
			request.setAttribute("itemsBill", itemsBill);
//			request.setAttribute("curryear", calendar.get(Calendar.YEAR));
//			request.setAttribute("currmonth", calendar.get(Calendar.MONTH)+1);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("user", currentUser);
			request.setAttribute("year", (yearStr==null || "".equals(yearStr))?calendar.get(Calendar.YEAR):Integer.parseInt(yearStr));
			request.setAttribute("month", (monthStr==null || "".equals(monthStr))?calendar.get(Calendar.MONTH)+1:Integer.parseInt(monthStr));
			request.setAttribute("site", site);
			
			return new ActionForward.Forward("/jsp2.0/admin/admin_billing_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_SITE_TOTAL_TRY_CATCH);
		}
		return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
	}
	
	/**
	 * 站点的所有主持人的账单列表
	 * @param portnums 端口数
	 * */
	@AsController(path = "listDetailBills")
	public Object listDetailBills(@CParam("portnums")String portnums, @CParam("year") String yearStr,
			@CParam("month") String monthStr,@CParam("pageNo") Integer pageNo,HttpServletRequest request){
		
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByAdminLogin(request);
		if(currentUser == null){
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_NO_ADMIN);
			return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
		}
		if(currentSite == null){
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_NO_SITE);
			return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//如果时间是null，默认设置上个月时间
			if(yearStr == null || monthStr == null){
				int date = calendar.get(Calendar.DATE);//日期
				int hours = calendar.get(Calendar.HOUR_OF_DAY);//时间
				//每月5日10点之后
				if(date == ConstantUtil.BILL_DATE && hours >= ConstantUtil.BILL_HOUR){
					calendar.add(Calendar.MONTH, -1);
				}else if(date < ConstantUtil.BILL_DATE){
					calendar.add(Calendar.MONTH, -2);
				}else if(date > ConstantUtil.BILL_DATE){
					calendar.add(Calendar.MONTH, -1);
				}else {
					calendar.add(Calendar.MONTH, -2);
				}
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				
				String start = year +"-"+ (month+1) +"-"+ minDay +" "+ "00:00:00";
				String end = year +"-"+ (month+1) +"-"+ maxDay +" "+ "23:59:59";
				startDate = sdf.parse(start);
				endDate = sdf.parse(end);
			}else{
				calendar.set(Calendar.YEAR, Integer.parseInt(yearStr));
				calendar.set(Calendar.MONTH, Integer.parseInt(monthStr) - 1);
				int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				String start = yearStr +"-"+ monthStr +"-"+ minDay +" 00:00:00";
				String end = yearStr +"-"+ monthStr +"-"+ maxDay + " 23:59:59";
				startDate = sdf.parse(start);
				endDate = sdf.parse(end);
			}
			
			//获取当前站点的全部主持人列表
			List<UserBase> userBases = null;
			//查询namehost指定端口数的主持人
			if(portnums!=null){
				userBases = userService.getuserBaseListForFixedPort(currentSite,startDate,endDate,portnums);
			}else{
				userBases = siteService.getHostListBySite(currentSite.getId());
			}
			
			if(userBases == null || userBases.isEmpty()){
				request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_NO_SITE_HOSTOR);
				return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
			}
			
			List<UserBilling> userBillings = new ArrayList<UserBilling>();
			Double sumCharge = 0.00;
			for(UserBase userBase : userBases){
				
				//获取该主持人的总账单
				List<Billing> billingBean = billingService.getUserBillingPage(0,0, userBase,currentSite, startDate, endDate, portnums);
				if(billingBean == null || billingBean.isEmpty()){
					continue;
				}
				//查询当前主持人的总金额
				sumCharge += billingService.getUserTotalTelFee(userBase,currentSite,startDate, endDate, portnums);
				//组织主持人总账单
				UserBilling userBilling = billingService.setUserTotalBilling(billingBean,userBase,currentSite,sumCharge);
				userBillings.add(userBilling);
			}

			Calendar currentCalendar = Calendar.getInstance();
			currentCalendar.setTime(new Date());
			request.setAttribute("curryear", currentCalendar.get(Calendar.YEAR));
			request.setAttribute("currmonth", currentCalendar.get(Calendar.MONTH)+1);
			request.setAttribute("userBillings", userBillings);
//			request.setAttribute("curryear", calendar.get(Calendar.YEAR));
//			request.setAttribute("currmonth", calendar.get(Calendar.MONTH)+1);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("user", currentUser);
			request.setAttribute("year", yearStr==null?calendar.get(Calendar.YEAR):Integer.parseInt(yearStr));
			request.setAttribute("month", monthStr==null?calendar.get(Calendar.MONTH)+1:Integer.parseInt(monthStr));
			request.setAttribute("site", currentSite);
			request.setAttribute("sumCharge", sumCharge);
			return new ActionForward.Forward("/jsp2.0/admin/admin_total_billing_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_USERS_LIST_TRY_CATCH);
		}
		return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
	}
	
	/**
	 * 查询指定主持人的账单明细
	 * @param hostId 会畅的主持人Id
	 * */
	@AsController(path = "viewBillDetail")
	public Object ViewBillDetail(@CParam("hostId") Integer hostId , @CParam("year") String yearStr,@CParam("month") String monthStr, 
			@CParam("pageNo") Integer pageNo,HttpServletRequest request){
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByAdminLogin(request);
		
		UserBase user = userService.getUserBaseById(hostId);
		if(user == null){
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_NO_USER);
			return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//如果时间是null，默认设置上个月时间
			if(yearStr == null || monthStr == null){
				int date = calendar.get(Calendar.DATE);//日期
				int hours = calendar.get(Calendar.HOUR_OF_DAY);//时间
				//每月5日10点之后
				if(date == ConstantUtil.BILL_DATE && hours >= ConstantUtil.BILL_HOUR){
					calendar.add(Calendar.MONTH, -1);
				}else if(date < ConstantUtil.BILL_DATE){
					calendar.add(Calendar.MONTH, -2);
				}else if(date > ConstantUtil.BILL_DATE){
					calendar.add(Calendar.MONTH, -1);
				}else {
					calendar.add(Calendar.MONTH, -2);
				}
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				
				String start = year +"-"+ (month+1) +"-"+ minDay +" "+ "00:00:00";
				String end = year +"-"+ (month+1) +"-"+ maxDay +" "+ "23:59:59";
				startDate = sdf.parse(start);
				endDate = sdf.parse(end);
			}else{
				calendar.set(Calendar.YEAR, Integer.parseInt(yearStr));
				calendar.set(Calendar.MONTH, Integer.parseInt(monthStr) - 1);
				int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				String start = yearStr +"-"+ monthStr +"-"+ minDay +" 00:00:00";
				String end = yearStr +"-"+ monthStr +"-"+ maxDay + " 23:59:59";
				startDate = sdf.parse(start);
				endDate = sdf.parse(end);
			}
			
			PageBean<ConfBilling> pageBean = null;
			if(currentSite.getHireMode() == 1){//包月
				pageBean = billingService.getConfBillingPage(pageNo, currentUser.getPageSize(), user, currentSite, startDate, endDate);
			}else {//计时
				pageBean = billingService.getConfBillingPage(pageNo, currentUser.getPageSize(), hostId, startDate, endDate);
			}
			
			Calendar currentCalendar = Calendar.getInstance();
			currentCalendar.setTime(new Date());
			request.setAttribute("curryear", currentCalendar.get(Calendar.YEAR));
			request.setAttribute("currmonth", currentCalendar.get(Calendar.MONTH)+1);
			//获取该主持人的总账单
			request.setAttribute("pageModel", pageBean);
//			request.setAttribute("curryear", calendar.get(Calendar.YEAR));
//			request.setAttribute("currmonth", calendar.get(Calendar.MONTH)+1);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("user", user);
			request.setAttribute("year", yearStr==null?calendar.get(Calendar.YEAR):Integer.parseInt(yearStr));
			request.setAttribute("month", monthStr==null?calendar.get(Calendar.MONTH)+1:Integer.parseInt(monthStr));
			request.setAttribute("site", currentSite);
			request.setAttribute("hostId", hostId);
			return new ActionForward.Forward("/jsp2.0/admin/admin_total_billing_detail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_USER_DETAIL_TRY_CATCH);
		}
		return new ActionForward.Forward("/jsp2.0/common/admin_bill_error.jsp");
	}
	
}
