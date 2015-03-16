package com.bizconf.vcaasz.interceptors;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.LicenseConstant;
import com.bizconf.vcaasz.entity.License;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.libernate.liberc.interceptor.SysInterceptorExt;

/**
 * 站点管理员与站点用户登录时，验证处理
 * 1.有license生效时，调用AS接口生效license
 * 2.有license失效时，调用AS接口失效license
 * ?该Interceptor拦截器是单例吗？
 * @author wangyong
 * 2013.5.3
 */
@Service
public class LicenseManageInterceptor implements SysInterceptorExt {
	
	private final Logger logger = Logger.getLogger(LicenseManageInterceptor.class);

	/*
	 * 特殊的instance变量
	 * 注：零长度的byte数组对象创建起来将比任何对象都经济――查看编译后的字节码：
	 * 生成零长度的byte[]对象只需3条操作码，而Object lock = new Object()则需要7行操作码。
	 */
	private byte[] lock = new byte[0]; 
	
	@Autowired 
	SiteService siteService;
	
	@Autowired
	LicService licService;
	
	@Override
	public Object doAfter(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws IOException {
		return null;
	}

	@Override
	public Object doBefore(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		synchronized(lock) {      //
			List<License> licenseList = licService.getSiteLicenseList(currentSite.getId());
			if(licenseList != null && licenseList.size() > 0){
				for(License lic:licenseList){
					if(lic.getEffeFlag().intValue() == LicenseConstant.HAS_NOT_EFFED.intValue() && lic.getExpireFlag().intValue() == LicenseConstant.HAS_NOT_EFFED.intValue() 
							&& !lic.getEffeDate().after(DateUtil.getGmtDate(null))){    //待生效状态
						if(!licService.effeLicense(lic)){
							logger.error("调用华为接口生效license错误:" + lic);
						}else{
							lic.setEffeFlag(LicenseConstant.HAS_EFFED);
							if(!licService.saveOrUpdate(lic)){
								logger.error("修改站点license生效错误：" + lic);
							}
						}
					}else if(!lic.getExpireDate().after(DateUtil.getGmtDate(null)) 
							&& lic.getExpireFlag().intValue() == LicenseConstant.HAS_NOT_EFFED.intValue()){   //待失效状态
						if(!licService.expiredLicense(lic)){
							logger.error("调用华为接口失效license错误:" + lic);
						}else{
							lic.setExpireFlag(LicenseConstant.HAS_EFFED);
							if(!licService.saveOrUpdate(lic)){
								logger.error("修改站点license失效错误：" + lic);
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public int getPriority() {
		return 50;
	}

}
