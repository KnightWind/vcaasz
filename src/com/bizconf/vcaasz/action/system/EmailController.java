package com.bizconf.vcaasz.action.system;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.entity.EmailConfig;
import com.bizconf.vcaasz.entity.EmailInfo;
import com.bizconf.vcaasz.entity.EmailTemplate;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.EmailConfigService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.EmailTemplateService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.JsonUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("email")
@Interceptors({SystemUserInterceptor.class})
public class EmailController {
	
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
	 * 系统管理员获取系统默认HOST配置。并返回页面
	 * @return
	 */
	@AsController(path = "showhost")
	public Object showHost(HttpServletRequest request,HttpServletResponse response){
		EmailConfig emailConfig=null;
		try {
			emailConfig=emailConfigService.getEmailSysConfig();
			if(emailConfig!=null){
				request.setAttribute("emailConfig", emailConfig);
			}
			SystemUser sysUser= userService.getCurrentSysAdmin(request);
			request.setAttribute("user", sysUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ActionForward.Forward("/jsp/system/email_config.jsp");
	}
	

	/**
	 * 系统管理员获取系统默认HOST配置。，并返回页面
	 * @return
	 */
	@AsController(path = "savehost")
	public Object saveHost(EmailConfig emailConfig,HttpServletRequest request,HttpServletResponse response){
		logger.info(emailConfig);
		Integer msgCode=0;
		SystemUser sysUser= userService.getCurrentSysAdmin(request);
		if(emailConfig!=null){
			try {
				if(emailConfig.getId()==null || emailConfig.getId()<1){
					emailConfig.setCreateUser(sysUser==null?0:sysUser.getId());
				}
				emailConfig=emailConfigService.saveSysConfig(emailConfig);
				request.setAttribute("user", sysUser);
				eventLogService.saveSystemEventLog(sysUser, EventLogConstants.SYSTEM_HOST_UPDATE,  "系统管理员修改Host配置", EventLogConstants.EVENTLOG_SECCEED, null, request);
				emailConfig=emailConfigService.getEmailSysConfig();
				if(emailConfig!=null){
					request.setAttribute("emailConfig", emailConfig);
				}
				msgCode=1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				msgCode=2;
			}
			request.setAttribute("msgCode", msgCode);
		}
		
		
		return new ActionForward.Forward("/jsp/system/email_config.jsp");
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
		Integer msgCode=0;
		try {
			emailConfig=emailConfigService.getEmailSysConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(emailConfig!=null){
			
			emailInfo.setCreateTime(new Date());
			emailInfo.setEmailSubject("测试发送邮件信息!");
			emailInfo.setContentType("html");
			emailInfo.setFromEmail(emailConfig.getEmailSender());
			emailInfo.setFromName(emailConfig.getEmailName());
			
			emailInfo.setValidate(true);
			emailInfo.setServerHost(emailConfig.getEmailHost());
			emailInfo.setServerPort("25");
			emailInfo.setUserName(emailConfig.getEmailAccount());
			emailInfo.setUserPass(emailConfig.getEmailPassword());
			
			emailInfo.setRetryCount(10); 
			
			emailService.saveEmailInfo(emailInfo);
			SystemUser sysUser= userService.getCurrentSysAdmin(request);
			request.setAttribute("user", sysUser);
			eventLogService.saveSystemEventLog(sysUser, EventLogConstants.SYSTEM_HOST_CHECK,  "系统管理员测试HOST时发送邮件", EventLogConstants.EVENTLOG_SECCEED, null, request);
			request.setAttribute("emailConfig", emailConfig);
			msgCode=3;
		}else{
			msgCode=4;
		}
		request.setAttribute("msgCode", msgCode);
//		return new ActionForward.Redirect("/system/email/showhost");
		return new ActionForward.Forward("/jsp/system/email_config.jsp");
	}
	
	/**
	 * 打开已经配置的邮件模板列表
	 * @return
	 */
	@AsController(path = "templist/{siteId:([0-9]+)}")
	public Object showEmailTempList(@CParam("siteId") int siteId,LiberInvocation invocation){
		List<EmailTemplate> tempLateList=null;
		logger.info("siteId-->>"+siteId);
		try {
			tempLateList=emailTemplateService.getTemplateListBySiteId(siteId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		invocation.getRequest().setAttribute("tempLateList",tempLateList);
		return new ActionForward.Forward("/jsp/system/email_template_list.jsp");
	}
	
	/**
	 * 修改已经配置的邮件模板或者是新增邮件模板
	 * @return
	 */
	@AsController(path = "template/{siteId:([0-9]+)}-{type:([0-9]+)}")
	public Object showEmailTemp(@CParam("siteId") int siteId,@CParam("type") int type,LiberInvocation invocation){
		EmailTemplate emailTemplate=null;
		
		//emailTemplate=emailTemplateService.getTemplateBySiteId(siteId, (short)7);
		
		invocation.getRequest().setAttribute("emailTemplate",emailTemplate);
		return new ActionForward.Forward("/jsp/system/email_template.jsp");
	}
	
	
	
	/**
	 * 保存Template对象
	 * @param emailTemplate
	 * @return
	 */
	@AsController(path = "saveTemplate")
	public Object saveEmailTemp(EmailTemplate emailTemplate){
		if(emailTemplate!=null){
			emailTemplateService.saveSysTemplate(emailTemplate);
		}
		return new ActionForward.Redirect("/system/email/templist");
	}
	
	
	@AsController(path = "goTemplate")
	public Object toEmailTemp(){
		return new ActionForward.Forward("/jsp/system/emailTemplate.jsp");
	}
	/**
	 * 系统管理员获取系统默认模板内容
	 * @return
	 */
	@AsController(path = "viewSysTemplate")
	public void switchTemplateByType(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control","no-cache");
		
		EmailTemplate template = null;
		PrintWriter out = null;
		String json = null;
		String strTempType = request.getParameter("tempType");
		try {
			out = response.getWriter();
			if(strTempType!=null && !"".equals(strTempType)){
				template = emailTemplateService.getSysTemplateByType(IntegerUtil.parseInteger(strTempType));
			}
			//替换...
			template=(EmailTemplate)ObjectUtil.parseChar(template, "emailContent");
//			template.setEmailContent(template.getEmailContent().replaceAll("\\$\\{siteaddress\\}",SiteIdentifyUtil.getCurrentDomine()));
			json = JsonUtil.parseJsonWithObject(template).toString();
			logger.info("the json object is :: "+json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	@AsController(path = "updateSysTemplate")
	public void updateSysTemplateContent(EmailTemplate template,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control","no-cache");
		PrintWriter out = null;
		String json = null;
		try {
			out = response.getWriter();
			//TODO 添加后台验证方法
//			template.setEmailContent(template.getEmailContent().replaceAll(SiteIdentifyUtil.getCurrentDomine(),"\\$\\{siteaddress\\}"));
			//template=(EmailTemplate)ObjectUtil.parseHtml(template, "emailContent");
			
			if(emailTemplateService.updateTemplateContent(template)){
				json = ConstantUtil.SUCCESS_FLAG;
				SystemUser sysUser= userService.getCurrentSysAdmin(request);
				eventLogService.saveSystemEventLog(sysUser, EventLogConstants.SYSTEM_TEMPLATE_UPDATE, "系统管理员修改了邮件模板", EventLogConstants.EVENTLOG_SECCEED, null, request); 
			}
		} catch (Exception e) {
			json = ConstantUtil.ERROR_FLAG;
			e.printStackTrace();
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
	@AsController(path = "recoverSysTemplate")
	public void recoverSysTemplateContent(@CParam("type")int type,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control","no-cache");
		
		EmailTemplate template = null;
		PrintWriter out = null;
		String json = "";
		try {
			out = response.getWriter();
			template = emailTemplateService.getSysRecoverTemplateByType(type);
			template=(EmailTemplate)ObjectUtil.parseChar(template, "emailContent");
//			template.setEmailContent(template.getEmailContent().replaceAll("\\$\\{siteaddress\\}", SiteIdentifyUtil.getCurrentDomine()));
			if(template!=null){
				json = JsonUtil.parseJsonWithObject(template).toString();
			}
			logger.info("the json object is :: "+json);
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
