package com.bizconf.vcaasz.component.language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;

@Component
public class LanguageComponent {
	
	static final String LANGUAGE_COOKIE_KEY = "LANG";
	
	public void setDefaultLanguage(HttpServletRequest request, HttpServletResponse response, String language) {
		try {
			CookieUtil.setCookie(response, LANGUAGE_COOKIE_KEY, language, 
					SiteIdentifyUtil.MEETING_CENTER_DOMAIN, 365);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDefaultLanguage(HttpServletRequest request) {

		String domain =  SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		return CookieUtil.getCookieByDomain(request, LANGUAGE_COOKIE_KEY,domain);
	}
}
