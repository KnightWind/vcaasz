package com.bizconf.vcaasz.util;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.ConfBase;

/**
 * @desc 生成URL的工具类
 * @author Darren
 * @date 2014-9-9
 */
public class UrlUtils {

	public static final String MEETING_CENTER_HTTP = "http://";
	public static final String MEETING_CENTER_DOMAIN = ".confcloud.cn";
	/**
	 * 生成入会URL
	 * */
	public static String getJionUrl(ConfBase conf , int type) {
		//对joinType和cId组织成一个含有bizconfzoom的字符串进行32位md5加密
		String encrypt = "bizconfzoom";
		MD5 md5 = new MD5();
		String joinUrl = "";
		if(type==ConfConstant.HOST_JOIN_TYPE_EMAIL){//主持人通过email入会
			joinUrl = "j/"+conf.getId()+""+ConfConstant.HOST_JOIN_TYPE_EMAIL+"/"+md5.encrypt(encrypt + ConfConstant.HOST_JOIN_TYPE_EMAIL + conf.getId());
		}else if(type==ConfConstant.USER_JOIN_TYPE_EMAIL){//参会者通过email入会
			joinUrl = "j/"+conf.getId()+""+ConfConstant.USER_JOIN_TYPE_EMAIL+"/"+md5.encrypt(encrypt + ConfConstant.USER_JOIN_TYPE_EMAIL + conf.getId());
		}
		
		joinUrl = "http://"+SiteIdentifyUtil.getCurrentDomine() + "/" + joinUrl;
		return joinUrl;
	}
	
	/**
	 * 生成下载中心的地址
	 * */
	public static String getDownLoadUrl(String downCenterURL){
		String domain = SiteIdentifyUtil.getCurrentDomine();
		
		String retURL = "http://" + domain + downCenterURL;
		return retURL;
		
	}
	
}
