package com.bizconf.vcaasz.action.admin;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.entity.EmailConfig;
import com.bizconf.vcaasz.entity.EmailInfo;
import com.bizconf.vcaasz.entity.EmailTemplate;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteAdminInterceptor;
import com.bizconf.vcaasz.service.EmailConfigService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.EmailTemplateService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.JsonUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("email")
@Interceptors({SiteAdminInterceptor.class})
public class EmailController extends BaseController{
	
	private final  Logger logger = Logger.getLogger(EmailController.class);
	
	@Autowired
	EmailConfigService emailConfigService;
	@Autowired
	EmailTemplateService emailTemplateService;
	@Autowired
	EmailService emailService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	UserService userService;
	
	/**
	 * 获取系统默认HOST配置。并返回页面
	 * @return
	 */
	@AsController(path = "showhost")
	public Object showHost(HttpServletRequest request){
		EmailConfig emailConfig=null;
		try {
			UserBase userBase = userService.getCurrentSiteAdmin(request);
			emailConfig=emailConfigService.getConfigBySiteId(userBase.getSiteId());
			if(emailConfig!=null){
				request.setAttribute("emailConfig", emailConfig);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionForward.Forward("/jsp/admin/email_config.jsp");
	}
	

	/**
	 * 保存系统默认HOST配置。，并返回页面
	 * @return
	 */
	@AsController(path = "savehost")
	public Object saveHost(EmailConfig emailConfig,HttpServletRequest request,HttpServletResponse response){
		logger.info(emailConfig);
		boolean saveFlag = false;
		Integer msgCode=0;
		UserBase currUser = userService.getCurrentSiteAdmin(request);
		String customFlag=request.getParameter("customFlag");
		
		String siteIdStr=request.getParameter("siteId");
		Integer siteId=IntegerUtil.parseInteger(siteIdStr);
		String defalut = "";
		if("0".equals(customFlag)){
			try {
				emailConfigService.deleteConfigBySiteId(siteId,currUser);
				emailConfig=emailConfigService.getEmailSysConfig();
				request.setAttribute("emailConfig", emailConfig);
				msgCode=1;
				saveFlag = true;
			} catch (Exception e) {
				e.printStackTrace();
				msgCode=2;
			}
			defalut = "使用系统默认配置 ";
			request.setAttribute("msgCode", msgCode);
		}else if("1".equals(customFlag)){
			if(emailConfig!=null){
				try {
					emailConfig.setCreateUser(currUser.getId());
					EmailConfig oldConfig = emailConfigService.getSiteConfig(siteId);
					if(oldConfig==null || oldConfig.getDelFlag().equals(ConstantUtil.DELFLAG_DELETED)){
						emailConfig.setId(null);
					}else{
						emailConfig.setId(oldConfig.getId());
					}
					emailConfig=emailConfigService.saveSiteConfig(emailConfig);
					if(emailConfig != null && emailConfig.getId().intValue() > 0){
						saveFlag = true;
					}
					request.setAttribute("emailConfig", emailConfig);
					msgCode=1;
					
				} catch (Exception e) {
					e.printStackTrace();
					msgCode=2;
				}
				defalut = "自定义配置";
				request.setAttribute("msgCode", msgCode);
			}
		}
		if(msgCode == 1){//设置成功
			sysHelpAdminEventLog(saveFlag, null, currUser, 
					EventLogConstants.SYSTEM_ADMIN_EMAILSERVER_SETUP, EventLogConstants.SITE_ADMIN_EMAILSERVER_SETUP, "配置邮箱服务器设置成功", defalut, request);
		}else{
			sysHelpAdminEventLog(saveFlag, null, currUser, 
					EventLogConstants.SYSTEM_ADMIN_EMAILSERVER_SETUP, EventLogConstants.SITE_ADMIN_EMAILSERVER_SETUP, "配置邮箱服务器设置失败", defalut, request);
		}
		return new ActionForward.Forward("/jsp/admin/email_config.jsp");
	}
	
	
	/**
	 * 测试发送邮件
	 * @param toEmail
	 * @param emailBody
	 * @return
	 */
	@AsController(path = "checkHost")
	public Object checkHost(@CParam("toEmail") String toEmail,@CParam("emailBody") String emailBody,HttpServletRequest request){
		EmailInfo emailInfo=new EmailInfo();
		emailInfo.setEmailAccepter(toEmail);
		emailInfo.setEmailContent(emailBody);
		EmailConfig emailConfig=null;
		boolean saveFlag = false;
		Integer msgCode=0;
		UserBase userBase = null;
		try {
			userBase = userService.getCurrentSiteAdmin(request);
			emailInfo.setSiteId(userBase.getSiteId());
			emailConfig=emailConfigService.getConfigBySiteId(userBase.getSiteId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String defaultObject = "邮件服务器信息测试";
		if(emailConfig!=null){
			
			emailInfo.setCreateTime(new Date());
			emailInfo.setEmailSubject("测试发送邮件信息!/Test send email");
			emailInfo.setContentType("html");
			emailInfo.setFromEmail(emailConfig.getEmailSender());
			emailInfo.setFromName(emailConfig.getEmailName());
			
			emailInfo.setValidate(true);
			emailInfo.setServerHost(emailConfig.getEmailHost());
			emailInfo.setServerPort("25");
			emailInfo.setUserName(emailConfig.getEmailAccount());
			emailInfo.setUserPass(emailConfig.getEmailPassword());
			emailInfo.setRetryCount(10); 
			saveFlag = emailService.saveEmailInfo(emailInfo);
			if(saveFlag){
				sysHelpAdminEventLog(saveFlag, null, userBase, 
						EventLogConstants.SYSTEM_ADMIN_EMAILTEST, EventLogConstants.SITE_ADMIN_EMAILTEST, "发送测试邮件成功", defaultObject, request);
			}else{
				sysHelpAdminEventLog(saveFlag, null, userBase, 
						EventLogConstants.SYSTEM_ADMIN_EMAILTEST, EventLogConstants.SITE_ADMIN_EMAILTEST, "发送测试邮件失败", defaultObject, request);
			}
			request.setAttribute("emailConfig", emailConfig);
			msgCode=3;
		}else{
			msgCode=4;
			sysHelpAdminEventLog(saveFlag, null, userBase, 
					EventLogConstants.SYSTEM_ADMIN_EMAILTEST, EventLogConstants.SITE_ADMIN_EMAILTEST, "发送测试邮件失败", defaultObject, request);
		}
		request.setAttribute("msgCode", msgCode);
		return new ActionForward.Forward("/jsp/admin/email_config.jsp");
	}
	 
	
	@AsController(path = "goTemplateEdit")
	public Object toEmailTemp(){
		return new ActionForward.Forward("/jsp/admin/emailTemplate.jsp");
	}
	/**
	 * 系统管理员获取系统默认模板内容
	 * @return
	 */
	@AsController(path = "viewSiteTemplate")
	public void switchTemplateByType(@CParam("tempType")Integer tempType,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control","no-cache");
		
		EmailTemplate template = null;
		PrintWriter out = null;
		String json = null;
		try {
			UserBase userBase = userService.getCurrentSiteAdmin(request);
			out = response.getWriter();
			template = emailTemplateService.getTemplateBySiteId(userBase.getSiteId(),tempType);
			template=(EmailTemplate)ObjectUtil.parseChar(template, "emailContent");
//			template.setEmailContent(template.getEmailContent().replaceAll("\\$\\{siteaddress\\}", SiteIdentifyUtil.getCurrentDomine()));
			json = JsonUtil.parseJsonWithObject(template).toString();
			//logger.info("the json object is :: "+json);
		} catch (Exception e) {
			json = ConstantUtil.ERROR_FLAG;
			e.printStackTrace();
		}finally{
			out.print(json);
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 修改系统默认模板内容
	 * @param request
	 * @param response
	 */
	@AsController(path = "updateSiteTemplate")
	public void updateSiteTemplateContent(EmailTemplate template,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control","no-cache");
		PrintWriter out = null;
		String json = null;
		try {
			out = response.getWriter();
			UserBase user= userService.getCurrentSiteAdmin(request);
			if(user==null){
				throw new RuntimeException("获取当前登录用户失败！");
			}
			template.setSiteId(user.getSiteId());
			template.setCreateUser(user.getId());
			//template=(EmailTemplate)ObjectUtil.parseHtml(template, "emailContent");
			boolean saveFlag = emailTemplateService.saveOrUpdateSiteTemplate(template);
			if(saveFlag){
				json = ConstantUtil.SUCCESS_FLAG;
				sysHelpAdminEventLog(saveFlag, userService.getCurrentSysAdmin(request), user, 
						EventLogConstants.SYSTEM_ADMIN_EMAILMODEL_SETUP, EventLogConstants.SITE_ADMIN_EMAILMODEL_SETUP, "修改系统默认模板内容成功", null, request);
			}else{
				json = ConstantUtil.ERROR_FLAG;
				sysHelpAdminEventLog(saveFlag, userService.getCurrentSysAdmin(request), user, 
						EventLogConstants.SYSTEM_ADMIN_EMAILMODEL_SETUP, EventLogConstants.SITE_ADMIN_EMAILMODEL_SETUP, "修改系统默认模板内容失败", null, request);
			
			}} catch (Exception e) {
			e.printStackTrace();
			json = ConstantUtil.ERROR_FLAG;
		}finally{
			JSONObject data = new JSONObject();
			data.put("message", json);
			out.print(data.toString());
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 恢复系统模板内容
	 * @param template
	 * @param request
	 * @param response
	 */
	@AsController(path = "recoverSiteTemplate")
	public void recoverSiteTemplateContent(@CParam("type")int type,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control","no-cache");
		
		EmailTemplate template = null;
		PrintWriter out = null;
		String json = "";
		try {
			out = response.getWriter();
			template = emailTemplateService.getSysTemplateByType(type);
			
			template=(EmailTemplate)ObjectUtil.parseChar(template, "emailContent");
//			template.setEmailContent(template.getEmailContent().replaceAll("\\$\\{siteaddress\\}", SiteIdentifyUtil.getCurrentDomine()));
			if(template!=null){
				json = JsonUtil.parseJsonWithObject(template).toString();
			}
			//logger.info("the json object is :: "+json);
		} catch (Exception e) {
			json = ConstantUtil.ERROR_FLAG;
			e.printStackTrace();
		}finally{
			out.print(json);
			out.flush();
			out.close();
		}
	}
}
