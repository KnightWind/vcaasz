package com.bizconf.vcaasz.util;

public class DoubleUtil {

	public static Double parseDouble(String str){
		Double d=null;
			try {
				d=Double.valueOf(str);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return d;
		
	}
}
