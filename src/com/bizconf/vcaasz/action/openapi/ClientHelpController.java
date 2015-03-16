package com.bizconf.vcaasz.action.openapi;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath(value = {"download"})
public class ClientHelpController {
	
	private Logger logger=Logger.getLogger(ClientHelpController.class);
	
	@AsController(path = "kip/econf_client_zh/WebHelp/econf_client_Hedex_bookmap.htm")
	public Object chineseHelp(@CParam("random") int random,HttpServletRequest request) {
		logger.info("random="+request.getParameter("random"));
		String helpUrl="http://"+SiteIdentifyUtil.getCurrentBrand()+".confcloud.cn/help";
		return new ActionForward.Redirect(helpUrl);
	}

	@AsController(path = "kip/econf_client_en/WebHelp/econf_client_Hedex_bookmap.htm")
	public Object englishHelp(@CParam("random") int random,HttpServletRequest request) {
		logger.info("random="+request.getParameter("random"));
		String helpUrl="http://"+SiteIdentifyUtil.getCurrentBrand()+".confcloud.cn/help";
		return new ActionForward.Redirect(helpUrl);
	}
	
	
	
}
