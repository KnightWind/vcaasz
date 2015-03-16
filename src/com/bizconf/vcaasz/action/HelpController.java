package com.bizconf.vcaasz.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.ReqPath;

/**
 * 帮助
 * @author wangyong
 *
 */
@ReqPath({"help"})
public class HelpController {
	
	@Autowired
	SiteService siteService;
	
	@Autowired
	UserService userService;
	
	/**
	 * portal平台帮助
	 * wangyong
	 * 2013-4-26
	 */
	@AsController(path = "")
	public Object help(HttpServletRequest request,HttpServletResponse response){
		SiteBase siteBase = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		UserBase userBase = userService.getCurrentUser(request);
		request.setAttribute("siteBase", siteBase);
		request.setAttribute("userBase", userBase);
		request.setAttribute("currentLang", LanguageHolder.getCurrentLanguage());
//		return new ActionForward.Forward("/jsp/user/help.jsp");
		return new ActionForward.Forward("/jsp/user/help_doc_potal.jsp");
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * Alan Liu
	 * 2013-11-20
	 */
	@AsController(path = "/doc")
	public Object helpForAdmin(HttpServletRequest request,HttpServletResponse response){
		SiteBase siteBase = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		request.setAttribute("siteBase", siteBase);
		return new ActionForward.Forward("/jsp/admin/help.jsp");
	}
}
