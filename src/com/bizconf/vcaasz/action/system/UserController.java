package com.bizconf.vcaasz.action.system;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.component.zoom.ZoomUserOperationComponent;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.logic.SiteAdminLogic;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.EmailConfigService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.EmailTemplateService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.service.EnterpriseAdminService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.service.MsService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.service.ValidCodeService;
import com.bizconf.vcaasz.util.DownLoadUtil;
import com.bizconf.vcaasz.util.ExcelUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

/**
 * 系统管理员操作用户信息
 * @author oustin_quan	
 * @date 2014.8.12
 */
@ReqPath("user")
@Interceptors({SystemUserInterceptor.class})
public class UserController extends BaseController{
	private final  Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	SiteService siteService;
	@Autowired
	EmailConfigService emailConfigService;
	@Autowired
	EmailTemplateService emailTemplateService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	EmailService emailService;
	@Autowired
	UserService userService;
	@Autowired
	SiteAdminLogic siteAdminLogic;
	@Autowired
	EmpowerConfigService empowerConfigService;
	@Autowired
	LicService licService;
	@Autowired
	MsService msService;
	@Autowired
	EnterpriseAdminService enterpriseAdminService;
	@Autowired 
	ZoomUserOperationComponent zoomUserOperator;
	
	@Autowired
	ValidCodeService validCodeService;
	
	@Autowired
	DataCenterService dataCenterService;
	
	@SuppressWarnings("unchecked")
	@AsController(path = "siteUserBaseAll")
	public Object siteUserBaseAll(@CParam("keyword") String keyword,@CParam("numPartis") int numPartis,@CParam("kefu") Integer kefu,@CParam("sortField") String sortField,@CParam("sortRule") String sortRule,@CParam("pageNo") int pageNo, HttpServletRequest request) throws Exception{
		PageBean<UserBase> pageModel = null;
		SystemUser  currentSysUser = userService.getCurrentSysAdmin(request);
		pageModel = enterpriseAdminService.getSiteUserBasesAll(keyword, sortField, sortRule, numPartis,pageNo,currentSysUser.getPageSize());
		request.setAttribute("keyword", keyword);
		//request.setAttribute("site", site);
		request.setAttribute("sortField", sortField);
		request.setAttribute("sortRule", sortRule);
		if(numPartis>0){
			request.setAttribute("numPartis", numPartis);
		}else{
			request.setAttribute("numPartis", "");
		}
		String cs ="second";
		if(kefu == 1){
			 cs = "first";
		}
		if(currentSysUser.isSystemClientServer() && cs.equals("first")){ 
			pageModel = new PageBean<UserBase>();
			request.setAttribute("firstIsCS", true);
		}
		if(currentSysUser.isSystemClientServer()){
			request.setAttribute("isCS", true);
		}
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp2.0/system/user_list.jsp");
	}
	/**
	 * 锁定用户
	 * oustin_quan
	 * 2014-08-19
	 */
	@AsController(path = "lock")
	public Object lockUser(@CParam("id") int id, HttpServletRequest request) throws Exception{
		boolean lockFlag = userService.lockUserBaseById(id);
		//boolean lockMeeting = userService.lockUserMeeting(user)
		String lockresult = "";
		if(lockFlag){
			lockresult = "success";
		}else{
			lockresult = "error";
		}
		request.setAttribute("lockresult", lockresult);
		return new ActionForward.Forward("/system/user/siteUserBaseAll");
	}
	
	/**
	 * 解锁用户
	 * oustin_quan
	 * 2014-08-19
	 */
	@AsController(path = "unlock")
	public Object unLockUser(@CParam("id") int id, HttpServletRequest request) throws Exception{
		boolean unlockFlag = userService.unlockUserBaseById(id);
		String result = "";
		if(unlockFlag){
			result = "success";
		}else{
			result = "error";
		}
		request.setAttribute("result", result);
		return new ActionForward.Forward("/system/user/siteUserBaseAll");
	}
	
	@AsController(path = "delHostUser")
	public Object delHostUser(@CParam("id") int id,HttpServletRequest request) throws Exception{
		UserBase user = userService.getUserBaseById(id);
		SystemUser currentUser = userService.getCurrentSysAdmin(request);
		boolean del = enterpriseAdminService.deleteUserBase(id, currentUser.getId());
		String delresult = "";
		if(del){
			DataCenter dataCenter = dataCenterService.queryDataCenterById(user.getDataCenterId());
			zoomUserOperator.delete(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId());
			delresult = "success";
		}else{
			delresult = "error";
		}
		request.setAttribute("delresult", delresult);
		return new ActionForward.Forward("/system/user/siteUserBaseAll");
	}
	
	
	@AsController(path = "resetEmail")
	public Object sendResetPwdEmail(@CParam("id") int id, HttpServletRequest request) throws Exception{
		UserBase user = userService.getUserBaseById(id);
		String domain=SiteIdentifyUtil.getCurrentDomine();
		String resetUrl = validCodeService.getResetPassUrlForUser(user,domain);
		try {
			emailService.resetPasswordEmail(user, resetUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return StringUtil.returnJsonStr(ConstantUtil.GLOBAL_FAIL_FLAG,"");
		}
		return StringUtil.returnJsonStr(ConstantUtil.GLOBAL_SUCCEED_FLAG,"");
	}
	
	/**
	 * 
	 * 导出主持人用户到Excel
	 * oustin_quan	
	 * 2014-08-20
	 */
	@AsController(path = "exportUsers")
	public void exportUsers(@CParam("keyword") String keyword,@CParam("numPartis") int numPartis,HttpServletRequest request,HttpServletResponse response) {
		List<UserBase> userList = enterpriseAdminService.exportSiteUserBase(keyword, numPartis);
		List<Object[]> exportList = new ArrayList<Object[]>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		//表格头部
		Object[] headers = new Object[7];
		headers[0] = ResourceHolder.getInstance().getResource("website.admin.adminlist.page.title.truename");//"姓名";
		headers[1] = ResourceHolder.getInstance().getResource("bizconf.jsp.admin.arrange_org_user.res9");//"登录邮箱";
		headers[2] = "过期时间";//"登录邮箱";
		headers[3] = ResourceHolder.getInstance().getResource("system.site.list.CompanyName");//"企业名称";
		headers[4] = ResourceHolder.getInstance().getResource("website.admin.adminlog.page.title.site.sign");//"站点标识";
		headers[5] = ResourceHolder.getInstance().getResource("bizconf.jsp.conf_default_setup.res16");//"最大参会方数";
		headers[6] = ResourceHolder.getInstance().getResource("website.system.userlist.createtime");//"创建日期";
		exportList.add(headers);
		
		//表格内容
		if(userList!=null && userList.size()>0){
			for (Iterator<UserBase> itr = userList.iterator(); itr.hasNext();) {
				UserBase user =  itr.next();
				SiteBase site = siteService.getSiteBaseByIdCaselessDeleted(user.getSiteId());
				Object[] userObjs = new Object[6];
				//userObjs[0] = 
				userObjs[0] = user.getTrueName();
				userObjs[1] = user.getUserEmail();
				
				if(user.getExprieDate()!=null){
					userObjs[2] = sdf1.format(user.getExprieDate());
				}else{
					userObjs[2] = "--";
				}
				
				if(site !=null){
					userObjs[3] = site.getSiteName();
					userObjs[4] = site.getSiteSign();
				}else{
					userObjs[3] = "";
					userObjs[4] = "";
				}
				userObjs[5] = user.getNumPartis();
				userObjs[6] = sdf.format(user.getCreateTime());
				exportList.add(userObjs);
			}
		}
		HSSFWorkbook wb = ExcelUtil.createExcelWorkbook("users",20,exportList);
		response.reset();
		response.setContentType("octets/stream");
        response.setHeader("Content-Disposition", "attachment;filename=users.xls");
        try {
        	wb.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			wb = null;
		}
	}
}
