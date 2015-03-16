package com.bizconf.vcaasz.action.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.user.ConfController;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LoginConstants;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteAdminInterceptor;
import com.bizconf.vcaasz.interceptors.SiteStatusInterceptor;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

/**
 * 
 * 企业管理员入口
 * 
 * @author Chris Gao [gaohl81@gmail.com]
 *
 */
@ReqPath("")
@Interceptors(SiteStatusInterceptor.class)
public class IndexController {
	
	private final Logger logger = Logger.getLogger(ConfController.class);
	
	@Autowired
	SiteService siteService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LoginService loginService;
	
	
	@Autowired
	ConfService confService;
	
	@AsController(path = "")
	@Interceptors({SiteAdminInterceptor.class})
	public Object index(LiberInvocation inv) {
		String siteBrand = SiteIdentifyUtil.getCurrentBrand();
		SiteBase siteBase = siteService.getSiteBaseBySiteSign(siteBrand);
		UserBase user = userService.getCurrentSiteAdmin(inv.getRequest());
		String domain = SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		String lang=CookieUtil.getCookieByDomain(inv.getRequest(), ConstantUtil.COOKIE_LANG_KEY,domain);
		inv.getRequest().setAttribute("user",user);
		inv.getRequest().setAttribute("siteBase", siteBase);
		inv.getRequest().setAttribute("lang", lang);
		
		//更新开始24小时仍未结束的会议状态
		confService.fixConfStatus();
		
		if(user.getPassEditor() != null && user.getPassEditor().intValue() != 0 && user.getPassEditor().intValue() != user.getId().intValue()){
			inv.getRequest().setAttribute("needResetPass", "true");      //超级站点管理员修改普通站点管理员密码后，普通站点管理员第一次登陆成功后需重置密码
		}
		return new ActionForward.Forward("/jsp/admin/index.jsp");
	}
	
	/**
	 * 系统管理员管理站点（跳转页面）
	 * wangyong
	 * 2013-5-13
	 */
	@AsController(path = "sysAdminManage/{siteId:([0-9]+)}")
	@Interceptors({SystemUserInterceptor.class})
	public Object sysAdminManage(@CParam("siteId") Integer siteId, LiberInvocation inv) {
		SiteBase siteBase = siteService.getSiteBaseById(siteId);
		inv.getRequest().setAttribute("siteId", siteId);
		inv.getRequest().setAttribute("siteBase", siteBase);
		return new ActionForward.Redirect("http://" + siteBase.getSiteSign() + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN + "/admin/" + "sysManage/" + siteId);
	}
	
	/**
	 * 系统管理员管理站点
	 * 模拟超级站点管理员登录
	 * wangyong
	 * 2013-5-13
	 */
	@AsController(path = "sysManage/{siteId:([0-9]+)}")
	@Interceptors({SystemUserInterceptor.class})
	public Object sysManage(@CParam("siteId") Integer siteId, LiberInvocation inv) {
		SiteBase siteBase = siteService.getSiteBaseById(siteId);
		UserBase siteAdmin = userService.getSiteSupperMasterBySiteId(siteId);
		int loginStatus = LoginConstants.LOGIN_ERROR_SUCCESS;
		try {
			loginStatus = loginService.loginSiteAdmin(siteBase, siteAdmin, inv.getResponse(), inv.getRequest());
		} catch (Exception e) {
			logger.error("模拟超级站点管理员登录异常" + "loginStatus:" + loginStatus + e);
		}
		if (loginStatus != LoginConstants.LOGIN_ERROR_SUCCESS) {
			logger.error("模拟超级站点管理员登录失败" + "loginStatus:");
		}
		String domain = SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		String lang=CookieUtil.getCookieByDomain(inv.getRequest(), ConstantUtil.COOKIE_LANG_KEY,domain);
		inv.getRequest().setAttribute("isSuperSiteAdmin", siteAdmin.getUserType().intValue() == 
			ConstantUtil.USERTYPE_ADMIN_SUPPER.intValue());
		inv.getRequest().setAttribute("user", siteAdmin);
		inv.getRequest().setAttribute("siteBase", siteBase);
		inv.getRequest().setAttribute("lang", lang);
		return new ActionForward.Forward("/jsp/admin/index.jsp");
	}
	
	@AsController(path="resetPass")
	public Object resetPass(HttpServletRequest request) throws Exception {
		return new ActionForward.Forward("/jsp/admin/resetPass.jsp");
	}
}
