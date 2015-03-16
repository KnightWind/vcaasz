package com.bizconf.vcaasz.util;

import java.util.Date;

import com.bizconf.vcaasz.entity.ConfBase;


public class ValidateUtil {
	
	
	/**
	 * 是否为数字
	 * @param integer
	 * @return
	 */
	public static boolean isInteger(Integer integer){
		boolean intFlag=false;
		
		return intFlag;
	}
	
	public static boolean isValiDate(Date date){
		
		return false;
	}
	
	/** 
	 * 判断日期格式:yyyy-mm-dd 
	 *  
	 * @param sDate 
	 * @return 
	 */  
	public static boolean isValidDate(String sDate) {  
//	    String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";  
//	    String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"  
//	            + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"  
//	            + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"  
//	            + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("  
//	            + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"  
//	            + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";  
//	    if ((sDate != null)) {  
//	        Pattern pattern = Pattern.compile(datePattern1);  
//	        Matcher match = pattern.matcher(sDate);  
//	        if (match.matches()) {  
//	            pattern = Pattern.compile(datePattern2);  
//	            match = pattern.matcher(sDate);  
//	            return match.matches();  
//	        } else {  
//	            return false;  
//	        }  
//	    }  
	    return false;  
	}  
	
	/**
	 * 校验是否会议信息被修改
	 * @param oldConfBase 原会议
	 * @param conf 新修改的会议
	 * */
	public static boolean isNotUpdate(ConfBase oldConfBase,ConfBase conf){
		
		if(oldConfBase.getHostKey().equals(conf.getHostKey()) 
				&& oldConfBase.getHostKey().equals(conf.getHostKey()) && oldConfBase.getConfName().equals(conf.getConfName())
				&& oldConfBase.getOptionStartType() == conf.getOptionStartType() 
				&& oldConfBase.getOptionJbh() == conf.getOptionJbh() 
				&& oldConfBase.getConfDesc().equals(conf.getConfDesc()) 
				&& oldConfBase.getDuration().intValue() == conf.getDuration().intValue()
				&& oldConfBase.getTimeZone().intValue() == conf.getTimeZone().intValue()){
			return true;
		}
		return false;
	}
}
