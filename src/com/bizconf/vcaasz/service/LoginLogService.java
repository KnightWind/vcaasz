package com.bizconf.vcaasz.service;

import com.bizconf.vcaasz.entity.LoginLog;
import com.bizconf.vcaasz.entity.PageBean;

/**
 * 用户登录日志
 * 
 * @author wangyong
 * 
 */
public interface LoginLogService {

	/**
	 * 查询用户登录日志
	 * 
	 * @param loginStatus 用户登录结果状态
	 *   0：全部，1：正常，2：异常
	 * @param userType 用户类型
	 *  "0",//全部
		"2",//普通站点用户
		"6",//系统客服
		"9",//站点管理员
		"99",//站点SUPPER 管理员
		"999",//系统管理员
		"9999",//系统SUPPER管理员
	 * @param loginName
	 *            用户的登录名
	 * @param sortField
	 *            排序字段
	 * @param sortord
	 *            排序方式 wangyong 2013-8-9
	 */
	public PageBean<LoginLog> getLoginLog(String siteName, String loginStatus,
			String userType, String loginTimeBegin, String loginTimeEnd, String loginName, Integer pageNo,int pageSize,
			String sortField, String sortord);
	
	/**
	 * 记录企业用户、站点管理员登录日志
	 * wangyong
	 * 2013-8-9
	 */
	public boolean savaUserLoginLog(LoginLog loginLog);
	
	/**
	 * 记录系统管理员登录日志
	 * wangyong
	 * 2013-8-9
	 */
	public boolean savaSysLoginLog(LoginLog loginLog);
	
	/**
	 * 企业管理员查看用户登录日志
	 * 2014-09-18 oustin_quan
	 */
	public PageBean<LoginLog> adminGetLoginLog(String nameOrEmail, int siteId, Integer pageNo,
			int pageSize, String sortField, String sortord);
}
