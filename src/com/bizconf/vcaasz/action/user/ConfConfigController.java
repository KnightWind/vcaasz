package com.bizconf.vcaasz.action.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.entity.DefaultConfig;
import com.bizconf.vcaasz.entity.EmpowerConfig;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.ZoomDefaultConfig;
import com.bizconf.vcaasz.interceptors.UserInterceptor;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.service.ZoomDefaultConfigService;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

/**
 * 企业用户会议缺省设置controller
 * @author wangyong
 * 2013/3/5
 */
@ReqPath("confConfig")
@Interceptors({UserInterceptor.class})
public class ConfConfigController extends BaseController {
	private final Logger logger = Logger.getLogger(ConfController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	ConfService confService;
	@Autowired
	SiteService siteService;
	@Autowired
	EmpowerConfigService empowerConfigService;
	@Autowired
	LicService licService;
	
	@Autowired
	ZoomDefaultConfigService zoomDefaultConfigService;
	/**
	 * 跳转到修改会议设置页面
	 * wangyong
	 * 2013-3-20
	 */
	@AsController(path = "getConfConfig")
	public Object getConfConfig(HttpServletRequest request) {
		UserBase currentUser = userService.getCurrentUser(request);
		
		ZoomDefaultConfig config = zoomDefaultConfigService.getUserDefaultConfig(currentUser.getId());
		request.setAttribute("config", config);
		request.setAttribute("currentUser", currentUser);
		
		return new ActionForward.Forward("/jsp2.0/user/conf_default_setup.jsp");
	} 
	
	/**
	 * 修改会议设置
	 * wangyong
	 * 2013-3-20
	 */
	@AsController(path = "updateConfConfig")
	public Object updateConfConfig(ZoomDefaultConfig config, HttpServletRequest request){
	
		if(zoomDefaultConfigService.saveOrUpdateConfig(config)){
			//"修改会议默认设置成功"
			setInfoMessage(request, ResourceHolder.getInstance().getResource("com.vcaas.portal.admin.setconfig.success"));
		}else{
			setErrMessage(request, ResourceHolder.getInstance().getResource("com.vcaas.portal.admin.setconfig.failed"));
		}
		return new ActionForward.Forward("/user/confConfig/getConfConfig");
	}

	
}
