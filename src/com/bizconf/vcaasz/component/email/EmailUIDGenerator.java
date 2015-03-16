package com.bizconf.vcaasz.component.email;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.entity.UserBase;
import com.libernate.liberc.utils.LiberContainer;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 
 * @description: 邮件UID生成器
 * @author Martin
 *
 * @date 2014年8月19日 上午10:23:48 
 *
 */
public class EmailUIDGenerator {
	
//	private static EmailUIDGenerator instance = new EmailUIDGenerator();
//	public static synchronized EmailUIDGenerator getInstance(){
//		return instance;
//	}
//	
	
	public static String getUid(Object... uidFactors){
		
		String uidStr = "";
		for (int i = 0; i < uidFactors.length; i++) {
			uidStr += uidFactors[i].toString();
			uidStr += "#";
		}
		return MD5.encodeToken(uidStr);
	}
	
	
	public static void main(String...strings){
		 
		String  a = getUid();
		
		System.out.println(a);
	}
}
