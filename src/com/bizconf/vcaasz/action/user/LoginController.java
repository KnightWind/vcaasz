package com.bizconf.vcaasz.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.encrypt.Base64;
import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.LanguageComponent;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LoginConstants;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.service.ValidCodeService;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Post;

/**
 * 站点用户登录
 * 
 * @author wangyong
 * 2013/2/28
 */
@ReqPath("login")
public class LoginController extends BaseController {
	private final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	LoginService loginService;
	@Autowired
	ValidCodeService validCodeService;
	@Autowired
	SiteService siteService;
	@Autowired
	UserService userService;
	@Autowired
	LanguageComponent languageComponent;

	@AsController(path = "")
	public Object login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (loginService.isLogined(request)) {
			return new ActionForward.Redirect("/user/");
		}
		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		request.setAttribute("siteBase", currentSite);
		return new ActionForward.Forward("/jsp/user/login.jsp");
	}

	/**
	 * 站点用户登录验证
	 * @param userBase 
	 * wangyong
	 * 2013-2-28
	 */
	@AsController(path = "check")
	@Post 
	public Object checkLogin(UserBase userBase,
			@CParam("authCode") String authCode, @CParam("type") String type,
			@CParam("random") String random, HttpServletRequest request,
			HttpServletResponse response) {
		if (loginService.isLogined(request)) {
//			return new ActionForward.Redirect("/user/");
			return returnJsonStr(LoginConstants.USER_LOGIN_SUCCESS, ResourceHolder.getInstance().getResource("bizconf.jsp.user.login.success"));
		}

		int loginStatus = LoginConstants.LOGIN_ERROR_SUCCESS;
/*		if (!validCodeService.checkValidCode(random, type, authCode)) {
			loginStatus = LoginConstants.LOGIN_ERROR_AUTHCODE;
//			setErrMessage(request, ResourceHolder.getInstance().getResource(
//					"system.login.error." + loginStatus));
//			return new ActionForward.Forward("/user/login");
			String errorMessage = ResourceHolder.getInstance().getResource("system.login.error." + loginStatus);
			return returnJsonStr(LoginConstants.USER_LOGIN_FAILED, errorMessage);
		}  */
		if (userBase != null) {
			String loginName = userBase.getLoginName();
			String loginPass = userBase.getLoginPass();

			if (StringUtil.isNotBlank(loginName)
					&& StringUtil.isNotBlank(loginPass)) {
				try {
					loginStatus = loginService.login(loginName, loginPass,
							authCode, response, request);
				} catch (Exception e) {
					logger.error("站点用户登录错误！",e);
				}
			}
			if (loginStatus != LoginConstants.LOGIN_ERROR_SUCCESS) {
//				setErrMessage(request, ResourceHolder.getInstance()
//						.getResource("system.login.error."+ loginStatus));
//				return new ActionForward.Forward("/user/login");
				String errorMessage = ResourceHolder.getInstance().getResource("system.login.error." + loginStatus);
				if (LoginConstants.LOGIN_ERROR_PASSWORD == loginStatus) {
					SiteBase errorUserSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
					UserBase errorUser = userService.getSiteUserByLoginName(errorUserSite.getId(), userBase.getLoginName());
					if(errorUser != null){
						errorMessage += ResourceHolder.getInstance().getResource("website.user.js.message.password.error.1")
								+ (ConstantUtil.MAX_ERROR_COUNT_USER - errorUser.getErrorCount())
								+ ResourceHolder.getInstance().getResource("website.user.js.message.password.error.2");
					}
				}
				return returnJsonStr(LoginConstants.USER_LOGIN_FAILED, errorMessage);
			}
		}
		
		//2013.7.4 登录成功后，用户缺省设置的语言设置到cookie中
		SiteBase site = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
//		UserBase user = userService.getSiteUserByLoginName(site.getId(), userBase.getLoginName());
		UserBase user = userService.getSiteUserByLoginName(site.getId(), userBase.getUserEmail());
		if(user != null && user.getId() != null && user.getId().intValue() > 0){
			languageComponent.setDefaultLanguage(request, response, user.getFavorLanguage());
		}
		//2013.7.4   ----结束
//		return new ActionForward.Redirect("/user/");
		return returnJsonStr(LoginConstants.USER_LOGIN_SUCCESS, ResourceHolder.getInstance().getResource("bizconf.jsp.user.login.success"));
	}
	
	private String returnJsonStr(int status, Object object){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("message", object == null ? "Login Error!" : object.toString());
		return json.toString();
	}
	
	
	@AsController(path = "formcheck")
	@Post
	public Object formLogin(UserBase userBase,
			@CParam("authCode") String authCode, @CParam("type") String type,
			@CParam("random") String random, HttpServletRequest request,
			HttpServletResponse response) {
		
		logger.info("form submit a login request ");

		//登录状态 
		int loginStatus = LoginConstants.LOGIN_ERROR_SUCCESS;
		
		if (loginService.isLogined(request)) {

			return new ActionForward.Redirect("/user/");
		}
		
	 
		
		if (userBase != null) {
			logger.info("user "+userBase.getLoginName()+" will login ");

			String loginName = userBase.getLoginName();
			String loginPass = userBase.getLoginPass();

			if (StringUtil.isNotBlank(loginName)
					&& StringUtil.isNotBlank(loginPass)) {
				try {
					loginStatus = loginService.login(loginName, loginPass,
							authCode, response, request);
				} catch (Exception e) {
					logger.error("站点用户登录错误！",e);
				}
			}
			
		}
		
		logger.info(" form  loginStatus is :" +loginStatus);
		if (loginStatus != LoginConstants.LOGIN_ERROR_SUCCESS) {
			String errorMessage = ResourceHolder.getInstance().getResource("system.login.error." + loginStatus);
			if (LoginConstants.LOGIN_ERROR_PASSWORD == loginStatus) {
				SiteBase errorUserSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
				UserBase errorUser = userService.getSiteUserByLoginName(errorUserSite.getId(), userBase.getLoginName());
				if(errorUser != null){
					errorMessage += ResourceHolder.getInstance().getResource("website.user.js.message.password.error.1")
							+ (ConstantUtil.MAX_ERROR_COUNT_USER - errorUser.getErrorCount())
							+ ResourceHolder.getInstance().getResource("website.user.js.message.password.error.2");
				}
			}
			setErrMessage(request, errorMessage);

			return new ActionForward.Forward("/jsp2.0/user/formlogin.jsp");
	}
	
	//2013.7.4 登录成功后，用户缺省设置的语言设置到cookie中
	SiteBase site = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
	UserBase user = userService.getSiteUserByLoginName(site.getId(), userBase.getLoginName());
	if(user != null && user.getId() != null && user.getId().intValue() > 0){
		languageComponent.setDefaultLanguage(request, response, user.getFavorLanguage());
	}
	logger.info("user "+userBase.getLoginName() +" login success!");
	
	logger.info("will redirect to index controller !");
	
	return new ActionForward.Redirect("/user/?var="+Base64.encode(userBase.getLoginName()));
  }
}
