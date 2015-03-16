package com.bizconf.vcaasz.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bizconf.vcaasz.constant.ConstantUtil;

public class CookieUtil {
	private static final Logger logger=Logger.getLogger(CookieUtil.class);


	 /**
	 *写COOKIE 
	 *参数：
	 *        cookieName        COOKIE名称
	 *        cookieValue        COOKIE的值
	 *        domainName        域名
	 *        cookieTime        COOKIE保存时间， 以秒为单位
	 *        response          HttpServletResponse 对象
	 *返回：
	 *        true         成功
	 *        False        失败
	 */
	public static boolean setCookie(HttpServletResponse response,
			String cookieName, String cookieValue, String domainName,
			int cookieTime) throws Exception {
		boolean flag = false;
		logger.info("setCookie cookieName="+cookieName+ ";    domain="+domainName+" ;    cookieValue="+cookieValue);
		try {
			String saveValue=cookieValue+ConstantUtil.COOKIE_SPLIT+domainName;
			Cookie cookie = new Cookie(cookieName, saveValue);
			cookie.setPath("/");
			cookie.setDomain(domainName);
			cookie.setMaxAge(24 * 3600 * cookieTime);
			response.addCookie(cookie);
			cookie = null;
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	 


	 /**
	 *  写页面有效COOKIE 
	 * 
	 * @param response  HttpServletResponse 对象
	 * @param cookieName COOKIE名称
	 * @param cookieValue COOKIE的值
	 * @param domainName 域名
	 * @return   true         成功;  False        失败
	 * @throws Exception
	 */
	public static boolean setPageCookie(HttpServletResponse response,
			String cookieName, String cookieValue, String domainName) throws Exception {
		boolean flag = false;

		logger.info("setPageCookie cookieName="+cookieName+ ";    domain="+domainName+" ;    cookieValue="+cookieValue);
		try {
			String saveValue=cookieValue+ConstantUtil.COOKIE_SPLIT+domainName;
			Cookie cookie = new Cookie(cookieName, saveValue);
			cookie.setPath("/");
			cookie.setDomain(domainName);
			response.addCookie(cookie);
			cookie = null;
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	 

	 
	 /**
	  * 读取COOKIE
	  * 参数：
	  *        cookieName        COOKIE名称
	  *        request           HttpServletRequest 对象
	  * 返回:
	  *     返回CookieName所对应的值 
	  * */
	public static String getCookie(HttpServletRequest request, String cookieName,String doma)  {
		String cookieValue = "";

		try {
			Cookie cookieArray[] = request.getCookies();
			if (cookieArray != null && cookieArray.length > 0) {
				String eachName="";
				for (Cookie cookie : cookieArray) {
//					cookie.getDomain()
					if (cookie != null) {
						eachName = cookie.getName();
						if(cookieName.equalsIgnoreCase(eachName)){
							logger.info("getCookie   cookieName=="+cookieName+" cookie.getDomain()="+cookie.getDomain());
						}
						//&& domain.equalsIgnoreCase(cookie.getDomain())
						if (cookieName.equalsIgnoreCase(eachName) ) {
							cookieValue += cookie.getValue();
							break;
						}
					}
				}
			}
			cookieArray = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("getCookie cookieName="+cookieName+" ; cookieValue="+cookieValue);
		return cookieValue;
	}
	
	

	 /**
	  * 读取COOKIE
	  * 参数：
	  *        cookieName        COOKIE名称
	  *        request           HttpServletRequest 对象
	  * 返回:
	  *     返回CookieName所对应的值 
	  * */
	public static String getCookieByDomain(HttpServletRequest request, String cookieName,String domain)  {
		String cookieValue = "";

		try {
			Cookie cookieArray[] = request.getCookies();
			if (cookieArray != null && cookieArray.length > 0) {
				String eachName="";
				String eachValue="";
				String splitValue="";
				String eachDomain="";
				String[] eachValueArray=null;
				int ii=1;
				for (Cookie cookie : cookieArray) {
//					cookie.getDomain()
					if (cookie != null) {
						eachName = cookie.getName();
						eachValueArray=null;
						eachDomain="";
						eachValue="";
						splitValue="";
						//
//						if(cookieName.equalsIgnoreCase(eachName)){
//							logger.info("getCookie   cookieName=="+cookieName+" cookie.getDomain()="+cookie.getDomain());
//						}
						// && domain.equalsIgnoreCase(cookie.getDomain())
						if (cookieName.equalsIgnoreCase(eachName) ) {
							splitValue=cookie.getValue();
							
							if(splitValue!=null && !"".equals(splitValue.trim())){
								eachValueArray=splitValue.split(ConstantUtil.COOKIE_SPLIT);
								if(eachValueArray!=null && eachValueArray.length ==2){
									eachValue=eachValueArray[0];
									eachDomain=eachValueArray[1];
								}
							}
							logger.debug("getCookieByDomain [ "+ii+" ]      eachName="+eachName+ "; eachDomain="+eachDomain+" ; splitValue="+splitValue);
							if(domain.equalsIgnoreCase(eachDomain)){
								cookieValue=eachValue;
								break;
							}
							ii++;
							//cookieValue += cookie.getValue();
							//break;
						}
					}
				}
			}
			cookieArray = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("getCookieByDomain cookieName="+cookieName+ "; domain="+domain+" ; cookieValue="+cookieValue);
		return cookieValue;
	}
	
	/**
	 * use to login cookie check
	 * @param request
	 * @param cookieName
	 * @param cValue
	 * @return
	 */
	public static boolean cookieExist(HttpServletRequest request, String cookieName,String cValue)  {
		String cookieValue = "";
		try {
			Cookie cookieArray[] = request.getCookies();
			if (cookieArray != null && cookieArray.length > 0) {
				String eachName="";
				for (Cookie cookie : cookieArray) {
					if (cookie != null) {
						eachName = cookie.getName();
						if (cookieName.equals(eachName)) {
							cookieValue = cookie.getValue();
							if(cookieValue.equals(cValue)){
								return true;
							}
						}
					}
				}
			}
			cookieArray = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	/**
	 * use to login cookie check
	 * @param request
	 * @param cookieName
	 * @param cValue
	 * @return
	 */
	public static boolean cookieExistByDomain(HttpServletRequest request,String domain, String cookieName,String cValue)  {
		String cookieValue =getCookieByDomain(request, cookieName,domain);
		if(cookieValue!=null && cookieValue.equals(cValue)){
			return true;
		}
//		try {
//			Cookie cookieArray[] = request.getCookies();
//			if (cookieArray != null && cookieArray.length > 0) {
//				String eachName="";
//				for (Cookie cookie : cookieArray) {
//					if (cookie != null) {
//						eachName = cookie.getName();
//						if (cookieName.equals(eachName)) {
//							cookieValue = cookie.getValue();
//							if(cookieValue.equals(cValue)){
//								return true;
//							}
//						}
//					}
//				}
//			}
//			cookieArray = null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return false;
	}
	
	/**
	 * 删除COOKIE
	 *参数：
	 *        cookieName        COOKIE名称
	 *        domainName        域名
	 *        response          HttpServletResponse 对象
	 *返回：
	 *        true         成功
	 *        False        失败
	 * */
	
	public static boolean deleteCookie(HttpServletResponse response,
			String cookieName, String domainName) throws Exception {
		boolean flag = false;
		try {
			Cookie cookie = new Cookie(cookieName, "");
			cookie.setPath("/");
			cookie.setDomain(domainName);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			cookie = null;
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
