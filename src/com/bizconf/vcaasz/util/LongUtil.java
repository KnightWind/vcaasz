package com.bizconf.vcaasz.util;

public class LongUtil {
	public static Long parseLong(String str){
		Long ln=null;
		if(str!=null && !"".equals(str) && !"null".equals(str.trim().toLowerCase())){
			try {
				ln=Long.valueOf(str);
			} catch (NumberFormatException e) {
//				e.printStackTrace();
				ln=null;
			}
		}
		return ln;
	}
}
