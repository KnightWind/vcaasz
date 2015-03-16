package com.bizconf.vcaasz.constant;

/**
 * 登录日志相关常量
 * @author wangyong
 * 2013.8.13
 */
public class LoginLogConstant {
	
	/*
	 * 平台类型：
	 *  "0",//全部
		"2",//普通站点用户
		"6",//系统客服
		"9",//站点管理员
		"99",//站点SUPPER 管理员
		"999",//系统管理员
		"9999",//系统SUPPER管理员
	 * */
	public static final String[] USER_TYPES = new String[]{
		"0",//全部
		"2",//普通站点用户
		"6",//系统客服
		"9",//站点管理员
		"99",//站点SUPPER 管理员
		"999",//系统管理员
		"9999"//系统SUPPER管理员
	};
	
	public static final String[] LOGIN_STATUS = new String[]{
		"0",//全部
		"1",//成功
		"2" //失败
	};
}
