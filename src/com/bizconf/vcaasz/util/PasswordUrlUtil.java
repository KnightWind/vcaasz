package com.bizconf.vcaasz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.encrypt.MD5;

public class PasswordUrlUtil {
	
	public static void main(String[] args) {
		SystemUser systemUser =new SystemUser();
		systemUser.setId(1);
		systemUser.setLoginName("bizadmin");
		systemUser.setLoginPass("2aefc34200a294a3cc7db81b43a81873");
//		System.out.println(MD5.encodePassword("admins","MD5"));
		String url=getResetPasswordUrlForSystemAdmin(systemUser,"dick.bizconf.cn");
		System.out.println(url);
		System.out.println(checkSystemAdminAuthCode(systemUser,"2985e9cbfd27a54b3bc01e23f234c358","1361164022242"));
	}
	///system/password/reset?ts=1361164022242&sid=10&ln=zfzst1980&auth=2985e9cbfd27a54b3bc01e23f234c358

	
	/**
	 * 生成系统管理员忘记密码的URL地址
	 * @param systemUser
	 * @return
	 * 2aefc34200a294a3cc7db81b43a81873
	 */
	public static String getResetPasswordUrlForSystemAdmin(SystemUser systemUser,String domain){
		StringBuffer urlBuffer=new StringBuffer();
		if(systemUser!=null && systemUser.getId()!=null && systemUser.getId().intValue()>0){
			if(domain==null || "".endsWith(domain.trim()) || "null".equals(domain.trim().toLowerCase())){
				domain=SiteIdentifyUtil.getCurrentDomine();//"www.bizconf.cn"
			}
			domain=domain.replace("http://", "");
			long ts=System.currentTimeMillis();
			String ln=systemUser.getLoginName();
			Integer sid = systemUser.getId();
			String op=systemUser.getLoginPass();
			urlBuffer.append("http://"+domain);
			urlBuffer.append("/system/password/reset?ts="+ts);
			urlBuffer.append("&sid="+sid);
			urlBuffer.append("&ln="+ln);
			String acs=ConstantUtil.PASSWORD_KEY_SYSTEM_ADMIN+""+sid+""+ln+""+op+""+ts;
			String auth=MD5.encodePassword(acs,"MD5");
			urlBuffer.append("&auth="+auth);
		}
		return urlBuffer.toString();
	}
	/**
	 * 
	 * 生产站点管理员忘记密码的URL地址
	 * @param systemUser
	 * @param domain
	 * @return
	 */
	public static String getResetPasswordUrlForAdmin(UserBase userBase,String domain){
		StringBuffer urlBuffer=new StringBuffer();
		if(userBase!=null && userBase.getId()!=null && userBase.getId().intValue()>0){
			if(domain==null || "".endsWith(domain.trim()) || "null".equals(domain.trim().toLowerCase())){
				domain=SiteIdentifyUtil.getCurrentDomine();//"www.bizconf.cn"
			}
			domain=domain.replace("http://", "");
			long ts=System.currentTimeMillis();
			String ln=userBase.getLoginName();
			Integer sid = userBase.getId();
			String op=userBase.getLoginPass();
			urlBuffer.append("http://"+domain);
			urlBuffer.append("/admin/password/reset?ts="+ts);
			urlBuffer.append("&sid="+sid);
			urlBuffer.append("&ln="+ln);
			String acs=ConstantUtil.PASSWORD_KEY_SITE_ADMIN+""+sid+""+ln+""+op+""+ts;
			String auth=MD5.encodePassword(acs,"MD5");
			urlBuffer.append("&auth="+auth);
		}
		return urlBuffer.toString();
	}	
	
	

	/**
	 * 
	 * 生产站点管理员忘记密码的URL地址
	 * @param systemUser
	 * @param domain
	 * @param ts 
	 * @return
	 */
	public static String getResetPasswordUrlForUser(UserBase userBase,String domain, long ts){
		StringBuffer urlBuffer=new StringBuffer();
		if(userBase!=null && userBase.getId()!=null && userBase.getId().intValue()>0){
			if(domain==null || "".endsWith(domain.trim()) || "null".equals(domain.trim().toLowerCase())){
				domain=SiteIdentifyUtil.getCurrentDomine();//"www.bizconf.cn"
			}
			domain=domain.replace("http://", "");
//			long ts=System.currentTimeMillis();
			String ln=userBase.getLoginName();
			Integer sid = userBase.getId();
			String op=userBase.getLoginPass();
			urlBuffer.append("http://"+domain);
			urlBuffer.append("/user/password/reset?ts="+ts);
			urlBuffer.append("&sid="+sid);
			urlBuffer.append("&ln="+ln);
			String acs=ConstantUtil.PASSWORD_KEY_USER+""+sid+""+ln+""+op+""+ts;
			String auth=MD5.encodePassword(acs,"MD5");
			urlBuffer.append("&auth="+auth);
		}
		return urlBuffer.toString();
	}	
	
	/**
	 * 系统管理员忘记密码时，根据Url地址中的参数验证Url中的AuthCode是否正确
	 * @param systemUser
	 * @param urlAuthCode
	 * @param urlTimeStamp
	 * @return
	 * 		true   验证码正确
	 * 		false  验证码错误
	 */
	public static boolean checkSystemAdminAuthCode(SystemUser systemUser,String urlAuthCode,String urlTimeStamp){
		boolean checkStatus=false;
		if(systemUser!=null && systemUser.getId()!=null && systemUser.getId().intValue()>0){
			String ln=systemUser.getLoginName();
			Integer sid = systemUser.getId();
			String op=systemUser.getLoginPass();
//			String acs=sid+""+ln+""+op+""+urlTimeStamp;
			String acs=ConstantUtil.PASSWORD_KEY_SYSTEM_ADMIN+""+sid+""+ln+""+op+""+urlTimeStamp;
			String auth=MD5.encodePassword(acs, "MD5").toLowerCase();
			if(urlAuthCode!=null && urlAuthCode.toLowerCase().equals(auth)){
				checkStatus=true;
			}
		}
		return checkStatus;
	}
	
	
	public static boolean checkSiteAdminAuthCode(UserBase userBase,String urlAuthCode,String urlTimeStamp){
		boolean checkStatus=false;
		if(userBase!=null && userBase.getId()!=null && userBase.getId().intValue()>0){
			String ln=userBase.getLoginName();
			Integer sid = userBase.getId();
			String op=userBase.getLoginPass();
			String acs=ConstantUtil.PASSWORD_KEY_SITE_ADMIN+""+sid+""+ln+""+op+""+urlTimeStamp;
			String auth=MD5.encodePassword(acs, "MD5").toLowerCase();
			if(urlAuthCode!=null && urlAuthCode.toLowerCase().equals(auth)){
				checkStatus=true;
			}
		}
		return checkStatus;
	}

	

	public static boolean checkSiteUserAuthCode(UserBase userBase,String urlAuthCode,String urlTimeStamp){
		boolean checkStatus=false;
		if(userBase!=null && userBase.getId()!=null && userBase.getId().intValue()>0){
			String ln=userBase.getLoginName();
			Integer sid = userBase.getId();
			String op=userBase.getLoginPass();//String acs=ConstantUtil.PASSWORD_KEY_USER+""+sid+""+ln+""+op+""+ts;
			String acs=ConstantUtil.PASSWORD_KEY_USER+""+sid+""+ln+""+op+""+urlTimeStamp;
			String auth=MD5.encodePassword(acs, "MD5").toLowerCase();
			if(urlAuthCode!=null && urlAuthCode.toLowerCase().equals(auth)){
				checkStatus=true;
			}
		}
		return checkStatus;
	}


	/**
	 * 根据Url的Timestamp值验证是否过期
	 * @param urlTimeStamp
	 * @return
	 * 		true   已经过期
	 * 		false  未过期
	 */
	public static boolean isExpireTimeStamp (String urlTimeStamp){
		boolean isExpire=true;
		if(urlTimeStamp!=null && !"".equals(urlTimeStamp.trim()) && !"null".equals(urlTimeStamp.trim().toLowerCase())){
			long nowTimestamp=System.currentTimeMillis();
			Long urlTimestamp=LongUtil.parseLong(urlTimeStamp);
			if(urlTimestamp!=null && urlTimestamp.longValue()>0){
				long tsvalue=urlTimestamp.longValue();		//生成找回密码url时间戳
				long tsdiff=nowTimestamp-tsvalue;			//计算当前时间戳与生成找回密码url时间戳的时间差
				long expirets=ConstantUtil.PASSWORD_URL_EXPIRE_HOURS.intValue() * 60 *60 *1000;
				if(tsdiff >0 && tsdiff <= expirets ){
					isExpire=false;
				}
			}
		}
		
		return isExpire;
		
	}
	
}
