package com.bizconf.vcaasz.logic;

import com.bizconf.vcaasz.entity.UserBase;

/**
 * 
 * @author Chris Gao
 *
 */
public interface SiteAdminLogic {
	
	/**
	 * 根据用户登录名，获取站点管理员信息
	 * 
	 * @param siteId
	 * @param loginName
	 * @return
	 */
	public UserBase getSiteAdmin(int siteId, String loginName);
	
	/**
	 * 根据email，获取站点管理员信息
	 * wangyong
	 * 2013-2-28
	 */
	public UserBase getSiteAdminByEmail(int siteId, String Email);
}
