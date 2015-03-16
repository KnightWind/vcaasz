package com.bizconf.vcaasz.constant;

/** 
 *   
 * @package com.bizconf.vcaasz.constant 
 * @description: 登录常量
 * @author Martin
 * @date 2014年8月22日 上午11:30:48 
 * @version 
 */
public interface LoginConstant {

	
	
	/**
	 * 用户登录成功标志
	 */
	public  final int USER_LOGIN_SUCCESS=0;
	
	/**
	 * 用户登录名错误
	 */
	public  final int USER_LOGIN_NAME_ERROR=1;
	
	/**
	 * 用户登录密码
	 */
	public  final int USER_PASS_ERROR=2;
	
	/**
	 * 用户登录验证码错误
	 */
	public  final int USER_AUTHCODE_ERROR=3;
	
	/**
	 * 用户站点已过期或不存在
	 */
	public  final int USER_SITE_ERROR=4;
}
