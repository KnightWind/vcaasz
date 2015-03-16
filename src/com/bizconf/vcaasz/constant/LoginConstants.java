package com.bizconf.vcaasz.constant;

/**
 * 
 * @author Chris
 *
 */
public class LoginConstants {
	
	public static final int USER_LOGIN_SUCCESS = 10;
	public static final int USER_LOGIN_FAILED = 11;
	public static final int LOGIN_ERROR_SUCCESS = 0;
	
	public static final int LOGIN_ERROR_USER_NOT_EXIST = 1;
	
	public static final int LOGIN_ERROR_PASSWORD = 2;
	
	public static final int LOGIN_ERROR_USER_LOCKED = 3;
	
	//登录用户已过期
	public static final int LOGIN_ERROR_USER_EXPRIED = 77;

	public static final int LOGIN_ERROR_UNKNOWN = 99;
	
	public static final int LOGIN_ERROR_AUTHCODE = 4;
	
	/** 主持人账号被锁定 */
	public static final int USER_STATU_LOCK = 5;
	
	public static final String SYSTEM_USER_SESSION_ID_KEY = "123!@#S!@#!@#!@#%$$%$%dfagFDF";
	
	public static final String SITE_ADMIN_SESSION_ID_KEY = "!@#S!123@#!@#!@#%$$%$%dfagFDF";
	
	public static final String USER_SESSION_ID_KEY = "!@#S!@#!@1222112#!@#%$$%$%dfagFDF";

	public static final String TIMEZONE = "timezone";
	public static final String SESSION_ID_NAME = "sessionid";
	
	public static final String UN_LOGIN_SESSION_ID_NAME = "tsessionid";
	
	public static final String SYSTEM_USER_SESSION_ID_NAME = "said";
	
	public static final String SITE_ADMIN_USER_SESSION_ID_NAME = "suid";
	
	public static final String USER_SESSION_ID_NAME = "uid";
}
