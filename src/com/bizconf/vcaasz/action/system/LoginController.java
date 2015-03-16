package com.bizconf.vcaasz.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.LoginConstants;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.service.ValidCodeService;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

/**
 * 系统管理员登录
 * 
 * @author zhaost
 * 
 */

@ReqPath({"/cs/login","login"})
public class LoginController extends BaseController {
	private final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	ValidCodeService validCodeService;
	@Autowired
	UserService userService;

	@AsController(path = "")
	public Object login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (loginService.isSysAdminLogined(request)) {
			return new ActionForward.Redirect("/system/");
		}

		return new ActionForward.Forward("/jsp/system/login_system.jsp");
	}

	/**
	 * 系统管理员登录
	 * 
	 * @param sysUser
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AsController(path = "check")
	public Object checkLogin(SystemUser sysUser,
			@CParam("authCode") String authCode, @CParam("type") String type,
			@CParam("random") String random, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (loginService.isSysAdminLogined(request)) {
			return new ActionForward.Redirect("/system/");
		}

		int loginStatus = LoginConstants.LOGIN_ERROR_SUCCESS;
		if (!validCodeService.checkValidCode(random, type, authCode)) {
			loginStatus = LoginConstants.LOGIN_ERROR_AUTHCODE;
			setErrMessage(request, ResourceHolder.getInstance().getResource(
					"system.login.error." + loginStatus));
			return new ActionForward.Forward("/system/login");
		}
		if (sysUser != null) {
			logger.info(sysUser.toString());
			String loginName = sysUser.getLoginName();
			String loginPass = sysUser.getLoginPass();

			if (StringUtil.isNotBlank(loginName)
					&& StringUtil.isNotBlank(loginPass)) {
				loginStatus = loginService.loginSysAdmin(loginName, loginPass,
						authCode, response, request);
			}

			if (loginStatus != LoginConstants.LOGIN_ERROR_SUCCESS) {
				setErrMessage(request, ResourceHolder.getInstance()
						.getResource("system.login.error."+ loginStatus));
				return new ActionForward.Forward("/system/login");
			}
		}
		return new ActionForward.Redirect("/system/");
	}
}
