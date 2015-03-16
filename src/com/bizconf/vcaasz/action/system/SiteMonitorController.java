package com.bizconf.vcaasz.action.system;

import java.net.InetAddress;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;
@ReqPath("siteMonitor")
public class SiteMonitorController extends BaseController{
	private final  Logger logger = Logger.getLogger(SiteMonitorController.class);
	
	@Autowired
	SiteService siteService;
	@Autowired
	UserService userService;
	
	@AsController(path = "list")
	public Object list(@CParam("pageNo") int pageNo, HttpServletRequest request) throws Exception{
		
		PageBean<SiteBase> pageModel = null;
		pageModel = siteService.queryASAllSites(pageNo, 500);
		request.setAttribute("pageModel", pageModel);
		StringBuilder textBudiler = new StringBuilder("");
		
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();
		String tail = "<br/>";
		textBudiler.append("当前服务器IP：");
		textBudiler.append(ip);
		textBudiler.append(tail);
		textBudiler.append(tail);
		if(pageModel!=null && pageModel.getDatas()!=null){
			for (Iterator<SiteBase> itr = pageModel.getDatas().iterator(); itr.hasNext();) {
				SiteBase site =  itr.next();
				textBudiler.append("企业名称：");
				textBudiler.append(site.getSiteName());
				textBudiler.append(tail);
				textBudiler.append("企业标志：");
				textBudiler.append(site.getSiteSign());
				textBudiler.append(tail);
				textBudiler.append("当前licence：");
				textBudiler.append(site.getLicense());
				textBudiler.append(tail);
				textBudiler.append("站点并发数：");
				textBudiler.append(site.getSyncConfNum());
				textBudiler.append(tail);
				textBudiler.append(tail);
				textBudiler.append(tail);
			}
		}
		return new ActionForward.Text(textBudiler.toString());
	}
	
}
