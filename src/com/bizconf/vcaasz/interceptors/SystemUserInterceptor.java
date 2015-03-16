package com.bizconf.vcaasz.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.UserService;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.interceptor.SysInterceptorExt;

@Service
public class SystemUserInterceptor implements SysInterceptorExt {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	UserService userService;
	
	@Override
	public Object doAfter(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws IOException {
		return null;
	}

	@Override
	public Object doBefore(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (!loginService.isSysAdminLogined(request)) {
			if(request.getRequestURL().toString().endsWith("/cs")){
				request.setAttribute("cslogin", true);
			}
			return new ActionForward.Forward("/system/login");
		}
		
		SystemUser systemUser = userService.getCurrentSysAdmin(request);
		request.setAttribute("currentSystemUser", systemUser);
		if (systemUser != null) {
			request.setAttribute("isSuperSystemAdmin", systemUser.getSysType().intValue()
					== ConstantUtil.USERTYPE_SYSTEM_SUPPER.intValue());
		}
		return null;
	}

	@Override
	public int getPriority() {
		return 50;
	}

}
