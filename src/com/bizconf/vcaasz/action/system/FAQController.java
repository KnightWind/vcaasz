package com.bizconf.vcaasz.action.system;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

@Interceptors({SystemUserInterceptor.class})
@ReqPath("faq")
public class FAQController extends BaseController {
	
	
	public Object doRequest(LiberInvocation inv) {
		inv.getRequest().setAttribute("currentLang", LanguageHolder.getCurrentLanguage());
		return new ActionForward.Forward("/jsp2.0/system/problem.jsp");
	}
	
	
}
