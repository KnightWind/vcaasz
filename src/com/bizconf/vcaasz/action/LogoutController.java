package com.bizconf.vcaasz.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.service.LoginService;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("logout")
public class LogoutController {
	
	@Autowired
	LoginService loginService;
	
	@AsController(path = "")
	public Object sysLogout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		loginService.logout(response, request);
		
		return new ActionForward.Redirect("/");
	}
}
