package com.bizconf.vcaasz.util;

public class IntegerUtil {
	
	
	
	public static Integer parseInteger(String str){
		Integer integer=null;
		if(str!=null ){
			try {
				integer=Integer.valueOf(str);
			} catch (NumberFormatException e) {
				try {
					integer=Integer.parseInt(str);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
			//	e.printStackTrace();
			}
		}
		return integer;
		
	}

	/**
	 * 转换String为Integer，空值默认转换为0
	 * shhc
	 * 2013-3-1
	 */
	public static Integer parseIntegerWithDefaultZero(String str){
		Integer integer=null;
		if(str!=null ){
			try {
				integer=Integer.valueOf(str);
			} catch (NumberFormatException e) {
				try {
					integer=Integer.parseInt(str);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
			//	e.printStackTrace();
			}
		}
		if(integer==null){
			integer=0;
		}
		return integer;
	}

	public static Integer parseInteger(Object obj){
		Integer integer=null;
		if(obj!=null ){
			try {
				integer=Integer.valueOf(obj.toString());
			} catch (NumberFormatException e) {
			//	e.printStackTrace();
			}
		}
		return integer;
		
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);//21 4748 3647  21.47亿
		System.out.println(Long.MAX_VALUE);//922 3372 0368 5477 5807  922.33亿亿
		System.out.println(Short.MAX_VALUE);//32767 

//		System.out.println(De.MAX_VALUE);//32767 
		
	}
}
