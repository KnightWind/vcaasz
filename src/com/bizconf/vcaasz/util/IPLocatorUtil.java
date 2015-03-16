package com.bizconf.vcaasz.util;

/**
 * 
 * @author Chris
 * 
 */
public class IPLocatorUtil {
	
	private static final ThreadLocal<String> IPLOCATOR_VALUES = new ThreadLocal<String>();

	public static void setDownloadServer(String server) {
		IPLOCATOR_VALUES.set(server);
	}
	
	public static void remove() {
		IPLOCATOR_VALUES.remove();
	}
	
	/**
	 * 获取当前的测试最优下载地址
	 * 
	 * @return
	 */
	public static String getCurrentDownloadServer() {
		return IPLOCATOR_VALUES.get();
	}
}
