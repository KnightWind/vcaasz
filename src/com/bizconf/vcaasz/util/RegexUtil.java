package com.bizconf.vcaasz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	/**
	 * 根据正则表达式验证字符串是否符合要求
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static boolean accordStringByPattern(String str, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	public static void main(String[] args){
//		String name = "^中国asdfsf";
//		String format = "^[a-zA-Z0-9_\\-&\u4e00-\u9fa5]{4,32}$";
//		System.out.println(accordStringByPattern(name,format));
		//([\\da-zA-Z]|[\\u4e00-\\u9fa5])+(-_\\.\\s)*([\\da-zA-Z]|[\\u4e00-\\u9fa5])+
		String trueNameRegex="^(([\\da-zA-Z]|[\\u4e00-\\u9fa5])+([-_\\.\\s]([\\da-zA-Z]|[\\u4e00-\\u9fa5])+)*){2,32}$";//"[a-zA-Z0-9_\\-&\\u4e00-\\u9fa5]{1,32}";
		
		String phoneRegex="^\\(?(\\+?(0{1,2})?86)?[\\)\\s-]?((0?1[358]\\d{9})|((0[1-9]\\d{1,2})[\\s-]?([1-9]\\d{6,7})(-[1-9]\\d{1,4})?))$";
		String userName="(086)013436982006";
		System.out.println(accordStringByPattern(userName,trueNameRegex));
		
//		Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
////		System.out.println(pattern.);
//		Matcher matcher = pattern.matcher("123chAa.A_na@as163.com;zfzst1980@163.com");
////		matcher.groupCount();//.matches()
//		 System.out.println("matcher.groupCount()=="+matcher.groupCount());
//		if(!matcher.matches()){
//			 System.out.println("不匹配");
//		}else{
//			System.out.println("匹配！");
//		}
	}

}
