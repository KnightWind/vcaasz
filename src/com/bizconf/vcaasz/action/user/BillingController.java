package com.bizconf.vcaasz.action.user;

import java.text.ParseException;
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
 * @desc 企业用户账单
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
	 * 列举主持人的总账单
	 * @throws ParseException 
	 * */
	@AsController(path = "listTotalBill")
	public Object listBilling(@CParam("year") String yearStr,@CParam("month") String monthStr,
			@CParam("pageNo") Integer pageNo,HttpServletRequest request) throws ParseException{
		
		UserBase currentUser = userService.getCurrentUser(request);
		if(currentUser == null){
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_NO_SITE_HOSTOR);
			return new ActionForward.Forward("/jsp2.0/common/user_bill_error.jsp");
		}
		SiteBase site = siteService.getSiteBaseById(currentUser.getSiteId());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date startDate = null;
		Date endDate = null;
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
			//查询当前主持人的总金额
			Double sumCharge = billingService.getUserTotalTelFee(currentUser,site, startDate, endDate,null);
			//获取该主持人的总账单
			List<Billing> billingBean = billingService.getUserBillingPage(0, 0, currentUser,site, startDate, endDate, null);
			UserBilling userBilling = null;
			if(billingBean != null && !billingBean.isEmpty()){
				//组织主持人总账单
				userBilling = billingService.setUserTotalBilling(billingBean,currentUser,site,sumCharge);
			}
			Calendar currentCalendar = Calendar.getInstance();
			currentCalendar.setTime(new Date());
			request.setAttribute("curryear", currentCalendar.get(Calendar.YEAR));
			request.setAttribute("currmonth", currentCalendar.get(Calendar.MONTH)+1);
			request.setAttribute("userBilling", userBilling);
//			request.setAttribute("curryear", calendar.get(Calendar.YEAR));
//			request.setAttribute("currmonth", calendar.get(Calendar.MONTH)+1);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("user", currentUser);
			request.setAttribute("year", yearStr==null?calendar.get(Calendar.YEAR):Integer.parseInt(yearStr));
			request.setAttribute("month", monthStr==null?calendar.get(Calendar.MONTH)+1:Integer.parseInt(monthStr));
			request.setAttribute("site", site);
			
			return new ActionForward.Forward("/jsp2.0/user/user_billing_list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_USER_TOTAL_TRY_CATCH);
		}
		return new ActionForward.Forward("/jsp2.0/common/user_bill_error.jsp");
	}
	
	/**
	 * 列举主持人的账单明细
	 * */
	@AsController(path = "listDetailBills")
	public Object listDetailBills(@CParam("year") String yearStr,@CParam("month") String monthStr,
			@CParam("pageNo") Integer pageNo,HttpServletRequest request){
		
		UserBase currentUser = userService.getCurrentUser(request);
		if(currentUser == null){
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_NO_SITE_HOSTOR);
			return new ActionForward.Forward("/jsp2.0/common/user_bill_error.jsp");
		}
		SiteBase site = siteService.getSiteBaseById(currentUser.getSiteId());
		
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
			
//			PageBean<ConfBilling> pageBean = billingService.getConfBillingPage(pageNo, currentUser.getPageSize(), currentUser.getId(), startDate, endDate);
			
//			List<ConfBilling> confBillings = new ArrayList<ConfBilling>();
			PageBean<ConfBilling> pageBean = null;
			if(site.getHireMode() == 1){//包月
				pageBean = billingService.getConfBillingPage(pageNo, currentUser.getPageSize(), currentUser, site, startDate, endDate);
			}else {//计时
				pageBean = billingService.getConfBillingPage(pageNo, currentUser.getPageSize(), currentUser.getId(), startDate, endDate);
			}
			
			Calendar currentCalendar = Calendar.getInstance();
			currentCalendar.setTime(new Date());
			request.setAttribute("curryear", currentCalendar.get(Calendar.YEAR));
			request.setAttribute("currmonth", currentCalendar.get(Calendar.MONTH)+1);
			request.setAttribute("pageModel", pageBean);
//			request.setAttribute("curryear", calendar.get(Calendar.YEAR));
//			request.setAttribute("currmonth", calendar.get(Calendar.MONTH)+1);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("user", currentUser);
			request.setAttribute("year", yearStr==null?calendar.get(Calendar.YEAR):Integer.parseInt(yearStr));
			request.setAttribute("month", monthStr==null?calendar.get(Calendar.MONTH)+1:Integer.parseInt(monthStr));
			request.setAttribute("site", site);
			return new ActionForward.Forward("/jsp2.0/user/user_billing_detail.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorCode", ConstantUtil.BILL_ERROR_CURRENTUSER_DETAIL_TRY_CATCH);
		}
		return new ActionForward.Forward("/jsp2.0/common/user_bill_error.jsp");
	}
	
}
