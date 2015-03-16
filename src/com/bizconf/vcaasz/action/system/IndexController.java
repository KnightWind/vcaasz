package com.bizconf.vcaasz.action.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath({"/cs",""})
@Interceptors({SystemUserInterceptor.class})
public class IndexController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ConfService confService;
	
	public Object doRequest(LiberInvocation invocation) {
		
		SystemUser user = userService.getCurrentSysAdmin(invocation.getRequest());
		invocation.getRequest().setAttribute("user",user);

		String domain =   SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		String lang=CookieUtil.getCookieByDomain(invocation.getRequest(), ConstantUtil.COOKIE_LANG_KEY,domain);
		invocation.getRequest().setAttribute("lang", lang);
		
		//更新开始24小时仍未结束的会议状态
		confService.fixConfStatus();
		
		if(user.getPassEditor() != null && user.getPassEditor().intValue() != 0 && user.getPassEditor().intValue() != user.getId().intValue()){
			invocation.getRequest().setAttribute("needResetPass", "true");      //系统管理员密码被超级系统管理员修改后，第一次登陆需重置密码
		}
		return new ActionForward.Forward("/jsp/system/index.jsp");
	}
	
	@AsController(path="resetPass")
	public Object resetPass(HttpServletRequest request) throws Exception {
		return new ActionForward.Forward("/jsp/system/resetPass.jsp");
	}
}
