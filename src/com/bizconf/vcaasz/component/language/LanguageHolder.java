package com.bizconf.vcaasz.component.language;

/**
 * 
 * @author Chris
 * 
 */
public class LanguageHolder {
	
	private static final ThreadLocal<String> CURRENT_LANGUAGE = new ThreadLocal<String>();
	
	private static final ThreadLocal<String> TESSION_IDS = new ThreadLocal<String>();
	

	public static final String DEFAULT_LANGUAGE = "zh-cn";
	
	public static void setCurrentLanguage(String language) {
		CURRENT_LANGUAGE.set(language);
	}
	
	public static void remove() {
		CURRENT_LANGUAGE.remove();
	}

	/**
	 * 获取当前语言选择
	 * 
	 * @return
	 */
	public static String getCurrentLanguage() {
		return CURRENT_LANGUAGE.get() == null ? DEFAULT_LANGUAGE : CURRENT_LANGUAGE.get();
	}
	
	public static void setTSessionId(String tsessionId) {
		TESSION_IDS.set(tsessionId);
	}
	
	public static void removeTSessionID() {
		TESSION_IDS.remove();
	}
	
	public static String getTSessionID() {
		return TESSION_IDS.get();
	}
}
