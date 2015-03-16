package com.bizconf.vcaasz.action;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.UserAgentUtils;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

/**
 * @desc 支持Controller
 * @author Darren
 * @date 2015-2-13
 */
@ReqPath({"support"})
public class SupportController {

	@Autowired
	SiteService siteService;
	
	@Autowired
	UserService userService;
	/**
	 * 客户端帮助
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
		return new ActionForward.Forward("/jsp/user/help_doc_client.jsp");
	}
	
	/**
	 * 下载中心
	 * @param s : 是否支持页面footer和背景图
	 * */
	@AsController(path = "download")
	public Object download(@CParam("s") Integer isSupport,HttpServletRequest request,HttpServletResponse response){
		
		String UserAgent = request.getHeader("User-Agent");
		String apkDownLoadUrl="";//andorid下载地址
		String ipkDownLoadUrl="";//ios设备下载地址
		String clientDownLoadUrl="";//非移动设备客户端下载地址
		String os = UserAgentUtils.checkOS(UserAgent);
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(ConstantUtil.CONFIG_CONF_PROPERTIES);
			
			apkDownLoadUrl = bundle.getString(ConstantUtil.OS_ANDROID);
			ipkDownLoadUrl = bundle.getString(ConstantUtil.OS_IPHONE);
			if(os.equals(ConstantUtil.OS_WIN7)){
				clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WIN7);
			}else if(os.equals(ConstantUtil.OS_WINXP)) {
				clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WINXP);
			}else if(os.equals(ConstantUtil.OS_MAC)){
				clientDownLoadUrl = bundle.getString(ConstantUtil.OS_MAC);
			}
			//如果是移动设备
			else{
				//设置个默认的Win7
				clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WIN7);
			}
			request.setAttribute("isSupport", isSupport);//jsp页面的footer和背景去掉
			request.setAttribute("apkDownLoadUrl", apkDownLoadUrl);
			request.setAttribute("ipkDownLoadUrl", ipkDownLoadUrl);
			request.setAttribute("clientDownLoadUrl", clientDownLoadUrl);
			request.setAttribute("system", os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String lan = LanguageHolder.getCurrentLanguage();
		if(lan!=null && "en-us".equals(lan)){
			return new ActionForward.Forward("/jsp/user/down_center_en.jsp");
		}
		return new ActionForward.Forward("/jsp/user/down_center_zh.jsp");
	}
	
}
