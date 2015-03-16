package com.bizconf.vcaasz.action.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.component.zoom.ZoomUserOperationComponent;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.UserInterceptor;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.EnterpriseAdminService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.encrypt.MD5;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Post;

@ReqPath("profile")
@Interceptors(UserInterceptor.class)
public class ProfileController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	SiteService siteService;
	@Autowired
	EnterpriseAdminService enterpriseAdminService;
	@Autowired
	DataCenterService dataCenterService;
	@Autowired 
	ZoomUserOperationComponent zoomUserOperator;
	/**
	 * 跳转到修改个人信息设置页面
	 * wangyong
	 * 2013-3-20
	 */
	@AsController(path = "")
	public Object getCurrentUserInfo(HttpServletRequest request) {
		UserBase currentUser = userService.getCurrentUser(request);
		request.setAttribute("currentUser", currentUser);
		request.setAttribute("siteBase", siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand()));
		return new ActionForward.Forward("/jsp/user/profile.jsp");
	}
	
	/**
	 * 跳转到修改密码页面
	 * wangyong
	 * 2013-10-23
	 */
	@AsController(path = "passPage")
	public Object passPage(HttpServletRequest request) {
		UserBase currentUser = userService.getCurrentUser(request);
		request.setAttribute("currentUser", currentUser);
		request.setAttribute("siteBase", siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand()));
		return new ActionForward.Forward("/jsp/user/profile_password.jsp");
	}
	
	/**
	 * 修改个人信息设置
	 * wangyong
	 * 2013-3-20
	 */
	@AsController(path = "setupInfo")
	public Object setupInfo(@CParam("oldpassword") String oldpassword, UserBase newUser, HttpServletRequest request){
		boolean updateFlag = false;
		UserBase currentUser = userService.getCurrentUser(request);
		if(newUser == null){
			setErrMessage(request, ResourceHolder.getInstance().getResource("website.user.profile.info.setup.failed"));
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_PROFILE_SETUP, "个人信息设置失败！",
					EventLogConstants.EVENTLOG_FAIL, currentUser, request);
		}
			
		if(StringUtil.isNotBlank(oldpassword) && !MD5.encodePassword(oldpassword, "MD5").equals(currentUser.getLoginPass())){
			this.setErrMessage(request, ResourceHolder.getInstance().getResource("website.user.profile.old.pass.error"));
			return new ActionForward.Forward("/user/profile");
		}
		
		//Add by Darren 2014-12-24
		DataCenter dataCenter = dataCenterService.queryDataCenterById(currentUser.getDataCenterId());
		
		if(StringUtil.isNotBlank(newUser.getLoginPass())){
			zoomUserOperator.modifyPassword(dataCenter.getApiKey(),dataCenter.getApiToken(),currentUser.getZoomId(), newUser.getLoginPass());
			currentUser.setLoginPass(MD5.encodePassword(newUser.getLoginPass(), "MD5"));
		}
		currentUser.setLoginName(newUser.getLoginName());
		currentUser.setTrueName(newUser.getTrueName());
		currentUser.setEnName(newUser.getEnName());
		currentUser.setUserEmail(newUser.getUserEmail());
		currentUser.setMobile(newUser.getMobile());
		
		if("false".equals(loginNameValidate(request))){   //验证登录名是否重复
			this.setErrMessage(request, ResourceHolder.getInstance().getResource("website.user.profile.loginname.exist"));
			request.setAttribute("currentUser", currentUser);
			return new ActionForward.Forward("/jsp/user/profile.jsp");
		}
		
		if(StringUtil.isNotBlank(currentUser.getUserEmail())){  //验证邮箱是否重复
			if("false".equals(emailValidate(currentUser.getUserEmail(), currentUser.getId(), currentUser.getSiteId()))){
				this.setErrMessage(request, ResourceHolder.getInstance().getResource("website.user.profile.email.exist"));
				request.setAttribute("currentUser", currentUser);
				return new ActionForward.Forward("/jsp/user/profile.jsp");
			}
		}
		
		zoomUserOperator.update(dataCenter.getApiKey(),dataCenter.getApiToken(),currentUser.getZoomId(), currentUser.getZoomType(), currentUser.getNumPartis(), 
				currentUser.getTrueName(), "", true, false);
		
		updateFlag = enterpriseAdminService.updateUserBase(currentUser);
		if(updateFlag){
			setInfoMessage(request,ResourceHolder.getInstance().getResource("website.user.profile.info.setup.success"));
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_PROFILE_SETUP, "个人信息设置成功！",
					EventLogConstants.EVENTLOG_SECCEED, currentUser, request);
		}else{
			setErrMessage(request, ResourceHolder.getInstance().getResource("website.user.profile.info.setup.failed"));
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_PROFILE_SETUP, "个人信息设置失败！",
					EventLogConstants.EVENTLOG_FAIL, currentUser, request);
			return new ActionForward.Forward("/user/profile");
		}
		return new ActionForward.Forward("/user/profile");
	}
	
	/**
	 * 修改密码
	 * wangyong
	 * 2013-10-23
	 */
	@AsController(path = "resetPass")
	@Post
	public Object resetPass(@CParam("oldpassword") String oldpassword, @CParam("loginPass") String loginPass, HttpServletRequest request){
		boolean updateFlag = false;
		UserBase currentUser = userService.getCurrentUser(request);
		if(StringUtil.isNotBlank(oldpassword) && !MD5.encodePassword(oldpassword, "MD5").equals(currentUser.getLoginPass())){
			this.setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.old.pass.error"));
			return new ActionForward.Forward("/user/profile/passPage");
		}
		if(StringUtil.isNotBlank(loginPass)){
			currentUser.setLoginPass(MD5.encodePassword(loginPass, "MD5"));
			currentUser.setLoginPassPlain(loginPass);
			
			//Add by Darren 2014-12-24
			DataCenter dataCenter = dataCenterService.queryDataCenterById(currentUser.getDataCenterId());
			updateFlag = zoomUserOperator.modifyPassword(dataCenter.getApiKey(),dataCenter.getApiToken(),currentUser.getZoomId(), loginPass) && enterpriseAdminService.updateUserBase(currentUser);
		}
		
		if(updateFlag){
			setInfoMessage(request,ResourceHolder.getInstance().getResource("website.user.profile.pass.setup.success"));
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_PROFILE_SETUP, "密码修改成功！",
					EventLogConstants.EVENTLOG_SECCEED, currentUser, request);
			
			
			if(loginPass.equals(oldpassword)){
				return new ActionForward.Forward("/user/profile/passPage");
			}else{
				return new ActionForward.Forward("/jsp/user/reset_password_ok.jsp");
			}
		}else{
			setErrMessage(request, ResourceHolder.getInstance().getResource("website.user.profile.pass.setup.failed"));
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_PROFILE_SETUP, "密码修改失败！",
					EventLogConstants.EVENTLOG_FAIL, currentUser, request);
			return new ActionForward.Forward("/user/profile/passPage");
		}
		//return new ActionForward.Forward("/user/profile/passPage");
	}
	
	/**
	 * 修改个人信息时验证登录名是否已存在
	 * return "false":已存在    "true":不存在
	 * wangyong
	 * 2013-3-20
	 */
	private String loginNameValidate(HttpServletRequest request){
		String loginName = request.getParameter("loginName");
		UserBase currentUser = userService.getCurrentUser(request);
		if(currentUser != null && StringUtil.isNotBlank(currentUser.getLoginName())){
			if(!currentUser.getLoginName().equals(loginName)){ //若登录名更改则查询登录名是否已存在
				UserBase otherUser = userService.getSiteUserByLoginName(currentUser.getSiteId(), loginName);
				if(otherUser != null && otherUser.getId() != null && otherUser.getId().intValue() > 0){
					return "false";
				}else{
					return "ture";
				}
			}
		}
		return "ture";
	}
	
	/**
	 * 修改个人信息时验证邮箱是否已存在
	 * return true(不存在) false(已存在)
	 * wangyong
	 * 2013-6-17
	 */
	private String emailValidate(String userEmail, int userId, int siteId){
		String flag = "true";
		if(StringUtil.isNotBlank(userEmail)){
			UserBase user = userService.getSiteUserByEmail(siteId, userEmail.trim());
			if(user != null && userId != 0 && user.getId().intValue() != userId){    //修改用户
				logger.info("邮箱名"+userEmail+"已存在!");
				flag = "false";
			}
		}
		return flag;
	}
}
