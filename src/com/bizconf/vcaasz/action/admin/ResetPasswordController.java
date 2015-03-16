package com.bizconf.vcaasz.action.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.service.ValidCodeService;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.JsonUtil;
import com.bizconf.vcaasz.util.PasswordUrlUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.encrypt.MD5;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
import com.libernate.liberc.annotation.httpmethod.Post;
@ReqPath("password")
public class ResetPasswordController  extends BaseController {
	private final  Logger logger = Logger.getLogger(ResetPasswordController.class);
	@Autowired
	UserService userService;
	@Autowired
	ValidCodeService validCodeService;
	@Autowired
	EmailService emailService;
	@Autowired
	SiteService siteService;
	
	@AsController(path = "forget")
	public Object forget(){
		return new ActionForward.Forward("/jsp/admin/password_forget.jsp");
	}
	

	
	
	@AsController(path = "sendEmail")
	@Post
	public Object sendUrlToEmail(@CParam("authCode") String authCode, @CParam("type") String type,
			@CParam("random") String random, HttpServletRequest request){
		Map<String, Object> results = new HashMap<String, Object>();
		String systemEmail=String.valueOf(request.getParameter("systemEmail"));
		if (!validCodeService.checkValidCode(random, type, authCode)) {
			results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
			results.put("message", ResourceHolder.getInstance().getResource("system.login.error.4"));
			return JsonUtil.parseMapToJsonStr(results);
		}
		logger.info(" sendemail email= "+systemEmail);
		if (systemEmail==null || StringUtil.isEmpty(systemEmail)) {
			results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
			results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.inviteFirst.res8"));
			return JsonUtil.parseMapToJsonStr(results);			
		}

		String siteSign = SiteIdentifyUtil.getCurrentBrand();
		SiteBase siteBase = siteService.getSiteBaseBySiteSign(siteSign);
		UserBase adminUser = userService.getSiteAdminByEmail(siteBase.getId(), systemEmail);
		if(adminUser==null){
			results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
			results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.admin.user.not.exist"));
			return JsonUtil.parseMapToJsonStr(results);
		}
		String domain = SiteIdentifyUtil.getCurrentDomine();
		String resetUrl = PasswordUrlUtil.getResetPasswordUrlForAdmin(adminUser,domain);
		domain=null;
		try {
			emailService.resetPasswordEmail(adminUser, resetUrl);
		} catch (Exception e) {
			e.printStackTrace();
			results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
			results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.system.license_list.res14"));
			return JsonUtil.parseMapToJsonStr(results);
		}
		results.put("status", ConstantUtil.EMAIL_FLAG_SECCEED);
		results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.email.send.success"));
		return JsonUtil.parseMapToJsonStr(results);
	}
	
	
	@AsController(path = "reset")
	@Get
	public Object reset(HttpServletRequest request){
		//http://dick.bizconf.cn/system/password/reset?ts=1361177403425&sid=10&ln=zfzst1980&auth=b196678615282c92c9c34e7a0d978bd8
		String ts=String.valueOf(request.getParameter("ts"));
		String sid=String.valueOf(request.getParameter("sid"));
		String ln=String.valueOf(request.getParameter("ln"));
		String auth=String.valueOf(request.getParameter("auth"));
		if(sid==null || "".equals(sid.trim()) || "null".equals(sid.trim().toLowerCase())){
			//链接地址错误,
			return new ActionForward.Forward("/jsp/admin/password_reset.jsp");
		}
		Integer sysId=IntegerUtil.parseInteger(sid);
		if(sysId==null || sysId.intValue() <= 0){
			//链接地址错误,
			return new ActionForward.Forward("/jsp/admin/password_reset.jsp");
		}
		
		UserBase adminUser = null;
		adminUser = userService.getUserBaseById(sysId);
		if(adminUser==null){
			//链接地址错误,
			return new ActionForward.Forward("/jsp/admin/password_reset.jsp");
		}else{
			boolean tsStatus=PasswordUrlUtil.isExpireTimeStamp(ts);
			//已经过期
			if(tsStatus){
				return new ActionForward.Forward("/jsp/admin/password_reset.jsp");
			}
			boolean  autoStatus=PasswordUrlUtil.checkSiteAdminAuthCode(adminUser, auth, ts);
			//autoCode错误
			if(!autoStatus){
				return new ActionForward.Forward("/jsp/admin/password_reset.jsp");
			}
			
			
		}
		request.setAttribute("ts", ts);
		request.setAttribute("sid", sid);
		request.setAttribute("ln", ln);
		request.setAttribute("auth", auth);
		return new ActionForward.Forward("/jsp/admin/password_reset.jsp");
	}

	
	@AsController(path = "save")
	@Post
	public Object savePassword(HttpServletRequest request){
		String ts=String.valueOf(request.getParameter("ts"));
		String sid=String.valueOf(request.getParameter("sid"));
		String ln=String.valueOf(request.getParameter("ln"));
		String auth=String.valueOf(request.getParameter("auth"));
		String lp=String.valueOf(request.getParameter("lp"));
		String clp=String.valueOf(request.getParameter("clp"));

		request.setAttribute("ts", ts);
		request.setAttribute("sid", sid);
		request.setAttribute("ln", ln);
		request.setAttribute("auth", auth);
		
		Map<String, Object> results = new HashMap<String, Object>();
		
		// 2013-11-15 开始：修复bug，添加验证码的验证
		String authCode = String.valueOf(request.getParameter("authCode"));
		String random = String.valueOf(request.getParameter("random"));
		String type = String.valueOf(request.getParameter("type"));
		if(StringUtils.isEmpty(authCode) || !validCodeService.checkValidCode(random, type, authCode)){
			results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
			results.put("message", ResourceHolder.getInstance().getResource("system.login.error.4"));
			return JsonUtil.parseMapToJsonStr(results);
		}
		// 2013-11-15 结束
		
		if(sid==null || "".equals(sid.trim()) || "null".equals(sid.trim().toLowerCase())){
			//页面中传递来的SID是空
			results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
			results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.email.find.pass.error4"));
			return JsonUtil.parseMapToJsonStr(results);	
		}
		Integer sysId=IntegerUtil.parseInteger(sid);
		if(sysId==null || sysId.intValue() <= 0){
			//页面中传递来的SID的值小于0或者是空
			results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
			results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.email.find.pass.error"));
			return JsonUtil.parseMapToJsonStr(results);	
		}
		
		UserBase adminUser = null;
		adminUser = userService.getUserBaseById(sysId);
		if(adminUser==null){//根据用户ID号获取不到用户对象
			//找回密码失败,请重新找回密码
			results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
			results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.email.find.pass.error.1"));
			return JsonUtil.parseMapToJsonStr(results);	
		}else{
			if(lp==null || lp.length()<=0 ){//
				//新密码为空
				results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
				results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.email.find.pass.error.2"));
				return JsonUtil.parseMapToJsonStr(results);	
			}else{
				if(!clp.equals(lp)){//密码不相等
					results.put("status", ConstantUtil.EMAIL_FLAG_FAIL);
					results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.email.find.pass.error.3"));
					return JsonUtil.parseMapToJsonStr(results);	
				}else{
					adminUser.setLoginPass(MD5.encodePassword(lp, "MD5"));
					userService.updateUserPassWord(adminUser,lp);//.updateSystemUser(systemUser);
				}
			}
		}
		results.put("status", ConstantUtil.EMAIL_FLAG_SECCEED);
		results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.admin.password_reset.res6"));
		return JsonUtil.parseMapToJsonStr(results);
	}
	
	
	/**
	 * 超级站点管理员修改普通站点管理员密码后，普通站点管理员第一次登陆成功后需重置密码
	 * wangyong
	 * 2013-5-31
	 */
	@AsController(path = "resetPass")
	@Post
	public Object resetPass(HttpServletRequest request){
		Map<String, Object> results = new HashMap<String, Object>();
		UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
		String orgPass = request.getParameter("orgPass");
		String newPass = request.getParameter("loginPass");
		if(!StringUtil.isNotBlank(orgPass) || !StringUtil.isNotBlank(newPass)){
			results.put("status", ConstantUtil.RESET_PASS_FAILED);
			results.put("message", ResourceHolder.getInstance().getResource("system.sysUser.loginPass.input"));
			return JsonUtil.parseMapToJsonStr(results);	
		}
		if(StringUtil.isNotBlank(orgPass)){
			String inputOrgPass = MD5.encodePassword(orgPass, "MD5");
			if(!currentSiteAdmin.getLoginPass().equals(inputOrgPass)){
				results.put("status", ConstantUtil.RESET_PASS_FAILED);
				results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.pass.error"));
				return JsonUtil.parseMapToJsonStr(results);	
			}
		}
		if (StringUtil.isNotBlank(newPass)) {
			currentSiteAdmin.setLoginPass(MD5.encodePassword(newPass, "MD5"));
			currentSiteAdmin.setPassEditor(currentSiteAdmin.getId());
			try {
				DAOProxy.getLibernate().updateEntity(currentSiteAdmin);
			} catch (Exception e) {
				logger.error("重置密码失败！"+e);
				results.put("status", ConstantUtil.RESET_PASS_FAILED);
				results.put("message",  ResourceHolder.getInstance().getResource("bizconf.jsp.reset.pass.error"));
				return JsonUtil.parseMapToJsonStr(results);	
			}
		}
		results.put("status", ConstantUtil.RESET_PASS_SUCCEED);
		results.put("message", ResourceHolder.getInstance().getResource("bizconf.jsp.reset.pass.success"));
		return JsonUtil.parseMapToJsonStr(results);
	}
}
