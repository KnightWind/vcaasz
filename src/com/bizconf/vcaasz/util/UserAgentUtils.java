package com.bizconf.vcaasz.util;

import com.bizconf.vcaasz.constant.ConstantUtil;


/**
 * @desc 
 * @author Administrator
 * @date 2014-7-18
 */
public class UserAgentUtils {
	/**
	 * 区分移动端和pc端
	 * */
	public static boolean isMobile(String userAgent, String type) {
		boolean flag = false;
		if(type!=null && "mobile".equals(type.toLowerCase())){
			flag = !!userAgent.toLowerCase().matches("/applewebkit.*mobile.*/");
		}else if (type!=null && "android".equals(type.toLowerCase())) {
			flag = userAgent.toLowerCase().indexOf("android")>-1;
		}else if (type!=null && "iphone".equals(type.toLowerCase())) {
			flag = userAgent.toLowerCase().indexOf("iphone")>-1;
		}else if (type!=null && "ipad".equals(type.toLowerCase())) {
			flag = userAgent.toLowerCase().indexOf("ipad")>-1;
		}
		return flag;
	}
	
	/**
	 * 检查客户端和app终端
	 * */
	public static String checkOS(String userAgent){
		
		String browserType = ConstantUtil.OS_WIN7;//默认是win7环境
		if(userAgent == null || "".equals(userAgent)){
			return browserType;
		}
		if(userAgent.toLowerCase().indexOf(ConstantUtil.OS_ANDROID)>-1){
			browserType = ConstantUtil.OS_ANDROID;
		}else if(userAgent.toLowerCase().indexOf(ConstantUtil.OS_IPHONE)>-1){
			browserType = ConstantUtil.OS_IPHONE; 
		}else if(!!userAgent.toLowerCase().matches("/applewebkit.*mobile.*/")){
			browserType = ConstantUtil.OS_MOBILE;
		}else if(userAgent.toLowerCase().indexOf(ConstantUtil.OS_IPAD)>-1){
			browserType = ConstantUtil.OS_IPAD;
		}else if(userAgent.toLowerCase().indexOf(ConstantUtil.OS_WINXP_NT_5_1)>-1 || userAgent.toLowerCase().indexOf(ConstantUtil.OS_WINDOWS_XP)>-1){
			browserType = ConstantUtil.OS_WINXP;
		}else if(userAgent.toLowerCase().indexOf(ConstantUtil.OS_WIN7_NT_6_1)>-1 || userAgent.toLowerCase().indexOf(ConstantUtil.OS_WINDOWS_7)>-1){
			browserType = ConstantUtil.OS_WIN7;
		}else if(userAgent.toLowerCase().indexOf(ConstantUtil.OS_MAC_MACTH)>-1){
			browserType = ConstantUtil.OS_MAC;
		}
		return browserType;
	}
	
}
