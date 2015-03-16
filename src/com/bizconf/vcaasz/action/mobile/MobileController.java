package com.bizconf.vcaasz.action.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.constant.LoginConstant;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.IpLocatorService;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("api")
public class MobileController {
	@Autowired
	SiteService siteService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	IpLocatorService ipLocatorService;
	
	@AsController(path = "login")
	public Object checkLogin(@CParam("login_name") String logiName, @CParam("password") String password,
			HttpServletRequest request,HttpServletResponse response) {
		
//		 	String siteSign = SiteIdentifyUtil.getCurrentBrand();
//			if(StringUtil.isEmpty(siteSign)){
//				siteSign = SiteIdentifyUtil.DEFAULT_SITE_BRAND;
//			}
			JSONObject json = new JSONObject();
			int result = LoginConstant.USER_LOGIN_SUCCESS;
			
//			siteSign = siteSign.toLowerCase();
//			SiteBase siteBase = siteService.getSiteBaseBySiteSign(siteSign);
//			if(siteBase == null || siteBase.isExpire()){
//				result = LoginConstant.USER_SITE_ERROR;
//				
//				json.put("result", result);
//				return json.toString();
//			}
			
			UserBase user = userService.getSiteUserByMobile(logiName);
			if(user == null){
				user = userService.getSiteUserByEmail(logiName);
			}
			if(user==null){
				result = LoginConstant.USER_LOGIN_NAME_ERROR;
				
				json.put("result", result);
				return json.toString();
			}
			
			if (!MD5.encodePassword(password, "MD5").equalsIgnoreCase(user.getLoginPass())) {
				result = LoginConstant.USER_PASS_ERROR;
				json.put("result", result);
				return json.toString();
			}
			json.put("result", result);
			json.put("user_id", user.getZoomId());
			json.put("pmi_id", user.getPmiId());
			json.put("token", user.getZoomToken());
			json.put("user_name", user.getTrueName());
			
			return  json.toString();
		}
}
