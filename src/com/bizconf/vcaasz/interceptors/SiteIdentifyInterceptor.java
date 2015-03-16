package com.bizconf.vcaasz.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.IPLocatorUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.libernate.liberc.annotation.CommonsInterceptor;
import com.libernate.liberc.interceptor.SysInterceptorExt;

@Component
@CommonsInterceptor
public class SiteIdentifyInterceptor implements SysInterceptorExt {
	
	Log logger = LogFactory.getLog(SiteIdentifyInterceptor.class);

	@Override
	public Object doAfter(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws IOException {
		SiteIdentifyUtil.remove();
		IPLocatorUtil.remove();
		return null;
	}

	@Override
	public Object doBefore(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String serverName = request.getServerName();

		String brand = SiteIdentifyUtil.DEFAULT_SITE_BRAND;

		if (serverName != null
				&& serverName.endsWith(SiteIdentifyUtil.MEETING_CENTER_DOMAIN)) {
			if (logger.isInfoEnabled()) {
				logger.info("current access server name:" + serverName);
			}
			brand = serverName.split("\\.")[0];
			if (serverName.startsWith(SiteIdentifyUtil.MEETING_CENTER_DOMAIN)) {
				brand = "www";
			}
			/*if ("www".equalsIgnoreCase(brand)) {
				brand = SiteIdentifyUtil.DEFAULT_SITE_BRAND;
			}*/
		}

		SiteIdentifyUtil.setSiteBrand(brand);
		
		request.setAttribute("siteBrand", brand);
		
		
		//get best download url from cookie
		String bestDownloadServer = CookieUtil.getCookieByDomain(request, "b_down_s", SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		IPLocatorUtil.setDownloadServer(bestDownloadServer);

		return null;
	}

	@Override
	public int getPriority() {
		return 100;
	}

}
