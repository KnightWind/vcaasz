package com.bizconf.vcaasz.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.LanguageComponent;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteAdminInterceptor;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Post;

@Interceptors({SiteAdminInterceptor.class})
@ReqPath("profile")
public class ProfileController extends BaseController {
	
	private final Logger logger = Logger.getLogger(ProfileController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	LanguageComponent languageComponent;
	public Object doRequest(LiberInvocation inv) {
		//获取当前登录用户
		return new ActionForward.Forward("/jsp2.0/admin/profile.jsp");
	}
	
	@AsController
	public Object submit(@CParam("orgloginPass") String orgPass,UserBase newUser, LiberInvocation inv) {
		boolean flag = false;
		String oldPass = inv.getRequest().getParameter("orgPass");
		UserBase oldUser = userService.getCurrentSiteAdmin(inv.getRequest());
		oldUser.setEnName(newUser.getEnName());
		oldUser.setLoginName(newUser.getLoginName());
		oldUser.setTrueName(newUser.getTrueName());
		oldUser.setUserEmail(newUser.getUserEmail());
		oldUser.setMobile(newUser.getMobile());
		oldUser.setPageSize(newUser.getPageSize());   // 2013.6.26 因客户需求新加常量，部分每页展示用户个人信息设置每页显示条数
		if(StringUtil.isNotBlank(oldUser.getUserEmail())){
			if("false".equals(emailValidate(oldUser.getUserEmail(), oldUser.getId(), oldUser.getSiteId()))){
				this.setErrMessage(inv.getRequest(), ResourceHolder.getInstance().getResource("siteAdmin.list.update.3"));
				inv.getRequest().setAttribute("currentSiteAdmin", oldUser);
				return new ActionForward.Forward("/jsp/admin/profile.jsp");
			}
		}
		
		if(StringUtil.isNotBlank(oldUser.getLoginName())){
			if("false".equals(loginNameValidate(oldUser.getLoginName(), oldUser.getId()))){
				this.setErrMessage(inv.getRequest(), ResourceHolder.getInstance().getResource("siteAdmin.list.update.4"));
				inv.getRequest().setAttribute("currentSiteAdmin", oldUser);
				return new ActionForward.Forward("/jsp/admin/profile.jsp");
			}
		}
		
		if(newUser != null && StringUtil.isNotBlank(newUser.getLoginPass())){
			if(StringUtil.isEmpty(oldPass)){
				this.setErrMessage(inv.getRequest(), ResourceHolder.getInstance().getResource("system.user.oldPass.input"));
				return new ActionForward.Forward("/admin/profile");
			}
		}
		
		if(StringUtil.isNotBlank(oldPass)&&!MD5.encodePassword(oldPass, "MD5").equals(oldUser.getLoginPass())){
			//原始密码输入错误！
			this.setErrMessage(inv.getRequest(),  ResourceHolder.getInstance().getResource("bizconf.jsp.pass.error"));
			return new ActionForward.Forward("/admin/profile");
			
		}else {
			if (newUser.getLoginPass() != null && newUser.getLoginPass().length() > 0) {
				oldUser.setLoginPass(MD5.encodePassword(newUser.getLoginPass(), "MD5"));
			}
			try {
				oldUser = DAOProxy.getLibernate().updateEntity(oldUser);
				if(oldUser != null && oldUser.getId() != null && oldUser.getId().intValue() > 0){
					flag = true;
				}
			} catch (Exception e) {
				flag = false;
				logger.error("企业管理员个人设置异常" + oldUser + e);
			} finally {
//				sysHelpAdminEventLog(flag, userService.getCurrentSysAdmin(inv.getRequest()), oldUser, 
//						EventLogConstants.SYSTEM_ADMIN_INFO_SETUP, EventLogConstants.SITE_ADMIN_INFO_SETUP, "企业管理员个人设置", oldUser, inv.getRequest());
			}
			if(flag){
				sysHelpAdminEventLog(flag, null, oldUser, 
						EventLogConstants.SYSTEM_ADMIN_INFO_SETUP, EventLogConstants.SITE_ADMIN_INFO_SETUP,ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.success"), oldUser, inv.getRequest());
				this.setInfoMessage(inv.getRequest(), ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.success"));
			}else{
				sysHelpAdminEventLog(flag, null, oldUser, 
						EventLogConstants.SYSTEM_ADMIN_INFO_SETUP, EventLogConstants.SITE_ADMIN_INFO_SETUP,ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.success"), oldUser, inv.getRequest());
				this.setErrMessage(inv.getRequest(), ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.failed"));
			}
		}
		return new ActionForward.Forward("/admin/profile");
	}
	
	public String loginNameValidate(String loginName, int adminId){
		UserBase user = userService.getUserBaseById(adminId);
		if(user==null){//说明是新增的
			return "true";
		}
		if(StringUtil.isNotBlank(loginName)){
			if(user.getLoginName().equals(loginName)){ //说明没有更改
				return "true";
			}
			UserBase otherUser = userService.getSiteAdminByLoginName(user.getSiteId(), loginName);
			if(otherUser==null){ //存在有相同登录名的账号
				return "true";
			}
		}
		return "false";
	}
	
	/**
	 * 修改站点管理员个人信息时验证邮箱是否已存在
	 * return true(不存在) false(已存在)
	 * wangyong
	 * 2013-6-17
	 */
	private String emailValidate(String userEmail, int userId, int siteId){
		String flag = "true";
		if(StringUtil.isNotBlank(userEmail)){
			UserBase siteAdmin = userService.getSiteAdminByEmail(siteId, userEmail.trim());
			if(siteAdmin != null && userId != 0 && siteAdmin.getId().intValue() != userId){    //修改用户
				logger.info("邮箱名"+userEmail+"已存在!");
				flag = "false";
			}
		}
		return flag;
	}
	
	@AsController(path = "toChangePwd")
	public Object toChangePwd(HttpServletRequest request){
		return new ActionForward.Forward("/jsp2.0/admin/profile_password.jsp");
	}
	
	@AsController(path = "changePwd")
	public Object changePwd(@CParam("oldpassword") String oldPass,@CParam("loginPass") String loginPass,HttpServletRequest request){
		boolean flag = true;
		UserBase oldUser = userService.getCurrentSiteAdmin(request);
		if(StringUtil.isEmpty(oldPass)){
			this.setErrMessage(request, ResourceHolder.getInstance().getResource("system.user.oldPass.input"));
			return new ActionForward.Forward("/jsp2.0/admin/profile_password.jsp");
		}
		if(StringUtil.isNotBlank(oldPass)&&!MD5.encodePassword(oldPass, "MD5").equals(oldUser.getLoginPass())){
			//原始密码输入错误！
			this.setErrMessage(request,  ResourceHolder.getInstance().getResource("bizconf.jsp.pass.error"));
			return new ActionForward.Forward("/jsp2.0/admin/profile_password.jsp");
		}else {
			oldUser.setLoginPass(MD5.encodePassword(loginPass, "MD5"));
			try {
				oldUser = DAOProxy.getLibernate().updateEntity(oldUser);
				this.setInfoMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.success"));
			} catch (Exception e) {
				logger.error("企业管理员个人设置异常" + oldUser + e);
				this.setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.failed"));
				flag = false;
			} finally {
//				sysHelpAdminEventLog(flag, null, oldUser, 
//						EventLogConstants.SYSTEM_ADMIN_INFO_SETUP, EventLogConstants.SITE_ADMIN_INFO_SETUP, "企业管理员密码修改", oldUser, request);
			}
			if(flag){
				sysHelpAdminEventLog(flag, null, oldUser, 
						EventLogConstants.SYSTEM_ADMIN_INFO_SETUP, EventLogConstants.SITE_ADMIN_INFO_SETUP, ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.success"), oldUser, request);
			}else{
				sysHelpAdminEventLog(flag, null, oldUser, 
						EventLogConstants.SYSTEM_ADMIN_INFO_SETUP, EventLogConstants.SITE_ADMIN_INFO_SETUP, ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.failed"), oldUser, request);

			}
		}
		return new ActionForward.Forward("/jsp2.0/admin/profile_password.jsp");
	}
	
	
	@AsController(path = "toFavorSetup")
	public Object toFavorSetup(HttpServletRequest request){
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		request.setAttribute("user", currentUser);
		return new ActionForward.Forward("/jsp2.0/admin/favor_setup.jsp");
	}
	
	@AsController(path = "updateFavor")
	@Post
	public Object updateFavor(UserBase user, HttpServletRequest request, HttpServletResponse response){
		if(user == null){
			setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.favor.input.error"));
			return new ActionForward.Forward("/jsp2.0/admin/favor_setup.jsp");
		}
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		try{
			//currentUser.setTimeZoneId(user.getTimeZoneId());
			//currentUser.setTimeZone(getTimeZone(user.getTimeZoneId()));
			currentUser.setPageSize(user.getPageSize());
			if(!currentUser.getFavorLanguage().equals(user.getFavorLanguage())){
				request.setAttribute("changelan", true);
			}
			currentUser.setFavorLanguage(user.getFavorLanguage());    //设置用户偏好语言版本
			if (StringUtil.isNotBlank(user.getFavorLanguage())) {
				languageComponent.setDefaultLanguage(request, response, user.getFavorLanguage()); //设置用户偏好语言版本（cookie）
			}
			boolean updateFlag = userService.saveUserBase(currentUser);
			if(updateFlag){
				setInfoMessage(request,ResourceHolder.getInstance().getResource("bizconf.jsp.user.favor.update.success"));
				saveAdminEventLog(updateFlag, currentUser, EventLogConstants.SITE_ADMIN_PREFERENCE_SETUP, ResourceHolder.getInstance().getResource("bizconf.jsp.user.favor.update.success"), currentUser, request);
				
				logger.info("用户修改偏好设置成功！" + currentUser);
			}else{
				setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.failed"));
				saveAdminEventLog(updateFlag, currentUser,EventLogConstants.SITE_ADMIN_PREFERENCE_SETUP , ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.failed"), currentUser, request);
				
				logger.info("用户修改偏好设置失败！" + currentUser);
			}
			request.setAttribute("user", currentUser);
		}catch (Exception e) {
			setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.failed"));
			saveAdminEventLog(false, currentUser, EventLogConstants.SITE_ADMIN_INFO_SETUP, ResourceHolder.getInstance().getResource("bizconf.jsp.user.profile.update.failed"), currentUser, request);
		}
		return new ActionForward.Forward("/jsp2.0/admin/favor_setup.jsp");
	}
	
}
