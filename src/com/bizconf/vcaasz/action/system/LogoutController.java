package com.bizconf.vcaasz.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.UserService;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("logout")
public class LogoutController {
	
	@Autowired
	LoginService loginService;
	@Autowired
	UserService userService;
	
	@AsController(path = "")
	public Object sysLogout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		loginService.logoutSysAdmin(response, request);
		SystemUser  currentSysUser = userService.getCurrentSysAdmin(request);
		if(currentSysUser.isSystemClientServer()){
			return new ActionForward.Redirect("/cs/login");
		}
		return new ActionForward.Redirect("/system/login");
	}
}
