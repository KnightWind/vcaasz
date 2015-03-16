package com.bizconf.vcaasz.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.UserService;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.interceptor.SysInterceptorExt;
/**
 * 
 * @author wangyong
 *
 */
@Service
public class UserInterceptor implements SysInterceptorExt {

	@Autowired
	LoginService loginService;
	@Autowired
	UserService userService;
	
	@Override
	public Object doAfter(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws IOException {
		System.out.println(System.currentTimeMillis()+"-after");
		
		return null;
	}

	@Override
	public Object doBefore(HttpServletRequest request, HttpServletResponse arg1)
			throws IOException {
		if (!loginService.isLogined(request)) {
			request.setAttribute("userSessionFlag", true);
			return new ActionForward.Forward("/user/login");
		}
		UserBase currentUser = userService.getCurrentUser(request);
		request.setAttribute("currentUser", currentUser);
		if(currentUser.isExpried()){
			return new ActionForward.Redirect("/user/logout");
		}
		return null;
	}

	@Override
	public int getPriority() {
		return 0;
	}

}
