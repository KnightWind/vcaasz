package com.bizconf.vcaasz.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.IpLocatorService;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("/")
public class IndexController {
	@Autowired
	SiteService siteService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	IpLocatorService ipLocatorService;
	
	public Object doRequest(LiberInvocation inv) {
		String siteBrand = SiteIdentifyUtil.getCurrentBrand();
		if ("www".equalsIgnoreCase(siteBrand)) {
			return new ActionForward.Redirect("http://" + SiteIdentifyUtil.DEFAULT_SITE_BRAND + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		}
		if(inv.getRequest().getServerName().contains(".vcaas.cn")){
			String brandString = inv.getRequest().getServerName();
			String[] brands = brandString.split("\\.");
			return new ActionForward.Redirect("http://" + brands[0] + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		}
		
		SiteBase siteBase = siteService.getSiteBaseBySiteSign(siteBrand);
		if (siteBase == null) {
			return new ActionForward.Error(404);
		}
		inv.getRequest().setAttribute("siteBase", siteBase);
		inv.getRequest().setAttribute("isLogined", loginService.isLogined(inv.getRequest()));
		
		//download server
		String clientIp = StringUtil.getIpAddr(inv.getRequest());
		String [] downloadServers = ipLocatorService.getDownloadServers(clientIp);
		String downloadServersStr = StringUtils.join(downloadServers, ",");
		inv.getRequest().setAttribute("downloadServers", downloadServersStr);
			
		return new ActionForward.Forward("/user");
		//return new ActionForward.Forward("/jsp/index.jsp");
	}
	
	/**
	 * 支持SLA
	 * */
	@AsController(path = "sla")
	public Object slaIndex(HttpServletRequest request,HttpServletResponse response){
		
		return new ActionForward.Forward("/static/page/cn/SLA.html");
	}
	
	/**
	 * 帮助
	 * */
	@AsController(path = "clientHelp")
	public Object clientHelp(HttpServletRequest request,HttpServletResponse response){
		
		return new ActionForward.Forward("/jsp2.0/userHelp/index.html");
	}
	@AsController(path = "clientHelpContent")
	public Object clientHelpContent(HttpServletRequest request,HttpServletResponse response){
		
		return new ActionForward.Forward("/jsp2.0/userHelp/clientHelpForCN.html?t="+new Date().getTime());
	}
}
