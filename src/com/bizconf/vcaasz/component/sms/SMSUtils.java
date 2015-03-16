package com.bizconf.vcaasz.component.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.bizconf.vcaasz.component.zoom.HttpReqeustUtil;

/**
 * @desc 
 * @author Administrator
 * @date 2014-9-3
 */
public class SMSUtils {

	//短信发送地址
	public final static String SMS_URL = "http://shanxi.ums86.com:8899/sms/Api/Send.do"; 
	
	public final static String SMSMSG_URL = "http://www.confcloud.cn/";
	//
	public final static String SPCODE = "210554";
	public final static String LOGINNAME = "sh_hc";
	public final static String PASSWORD = "tx5173";
	public final static String SERIALNUMBER = "";
	public final static String SCHEDULETIME = "";
	public final static String EXTENDACCESSNUM = "";
	public final static String F = "1";
	
	//组织发送请求Map(POST)
	public static Map<String, String>  smsAuthPost(){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("SpCode", SPCODE);						//企业编号
		map.put("LoginName", LOGINNAME);				//用户名称
		map.put("Password", PASSWORD);					//用户密码
		map.put("SerialNumber", SERIALNUMBER);			//流水号，20位数字，唯一 
		map.put("ScheduleTime", SCHEDULETIME);			//预约发送时间，格式:yyyyMMddhhmmss,如‘20090901010101’，立即发送请填空
		map.put("ExtendAccessNum", EXTENDACCESSNUM);	//接入号扩展号
		map.put("f", F);								//提交时检测方式	1 --- 提交号码中有效的号码仍正常发出短信，无效的号码在返回参数faillist中列出
//		map.put("UserNumber", "15242247000");			//手机号码(多个号码用”,”分隔)，最多1000个号码
//		String encoder = URLEncoder.encode("测试短信接口", "GB2312");
//		map.put("MessageContent" , encoder);		//短信内容, 最大700个字符
		
		return map;
	}
	
	
	//组织发送请求Map
	public static String smsAuthGet(String msgContent,String UserNumber){
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("?");
		buffer.append("SpCode="+SPCODE);
		buffer.append("&");
		buffer.append("LoginName="+LOGINNAME);
		buffer.append("&");
		buffer.append("Password="+PASSWORD);
		buffer.append("&");
		buffer.append("MessageContent="+msgContent);
		buffer.append("&");
		buffer.append("UserNumber="+UserNumber);
		buffer.append("&");
		buffer.append("SerialNumber="+SERIALNUMBER);
		buffer.append("&");
		buffer.append("ScheduleTime="+SCHEDULETIME);
		buffer.append("&");
		buffer.append("f="+F);
		
		return buffer.toString();
	}
	
	
	
	public static Map<String, String> sendSMS(String content, String phone){
		 
		try {
			String msgContent = URLEncoder.encode(content,"GBK");
			String retParam = smsAuthGet(msgContent, phone);
			String responseContent = HttpReqeustUtil.sendMsgByGet(SMS_URL+retParam);
			Map<String, String> retMap = new HashMap<String, String>();
	        
			if(responseContent!=null){
	        	//将返回结果组织成Map的形式
	        	String[] keyValue = responseContent.split("&");
	        	for(int i = 0;i < keyValue.length; i++){
	        		String[] keyValTemp = keyValue[i].split("=");
	        		if(keyValTemp.length == 1){
		        		retMap.put(keyValTemp[0], "");
		        		continue;
	        		}
	        		retMap.put(keyValTemp[0], keyValTemp[1]);
	        	}
	        }
	        return retMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
