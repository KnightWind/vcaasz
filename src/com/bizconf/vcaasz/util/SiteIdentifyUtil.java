package com.bizconf.vcaasz.util;

/**
 * 
 * @author Chris
 * 
 */
public class SiteIdentifyUtil {
	
	//public static final String MEETING_CENTER_DOMAIN = "vcaas.cn";
	public static final String MEETING_CENTER_DOMAIN = "confcloud.cn";
	
	public static final String DEFAULT_SITE_BRAND = "meeting";
	
	private static final ThreadLocal<String> SITE_FLAGS = new ThreadLocal<String>();

	public static void setSiteBrand(String brand) {
		SITE_FLAGS.set(brand);
	}
	
	public static void remove() {
		SITE_FLAGS.remove();
	}
	
	/**
	 * 获取当前访问的站点标识
	 * 
	 * @return
	 */
	public static String getCurrentBrand() {
		return SITE_FLAGS.get();
	}
	
	public static String getCurrentDomine(){
		return SITE_FLAGS.get()+"."+MEETING_CENTER_DOMAIN;
	}
}
