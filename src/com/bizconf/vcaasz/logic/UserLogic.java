package com.bizconf.vcaasz.logic;

import java.util.Date;
import java.util.List;

import com.bizconf.vcaasz.entity.UserBase;

public interface UserLogic {
	
 
	public UserBase getUserBaseByLoginName(String loginName,Integer siteId);
	
	
	
	/**
	 * 根据zoom的ID查询用户
	 * @param hostId
	 * @return
	 */
	public UserBase getUserBaseByZoomId(String hostId);
	
	
	/**
	 * 获取该站点下所有的主持人账号
	 * @param siteId
	 * @return
	 */
	public List<UserBase> getSiteHosts(int siteId);
	
	
	/**
	 * 锁定某个站点下的所有主持人账号
	 * @param siteId
	 * @return
	 */
	public boolean lockSiteHosts(int siteId);
	
	/**
	 * 解锁这个站点下所有用户
	 * @param siteId
	 * @return
	 */
	public boolean unLockSiteHosts(int siteId);
	
	
//	public boolean lockHost(UserBase host);
//	public boolean unLockHost(int siteId);
	
	
	/**
	 * 通过Email查询用户，包括已逻辑删除的用户
	 * @param email
	 * @return
	 */
	public UserBase getUserBaseByEmail(String email);

	/**
	 * 通过pmiId查询用户，包括已逻辑删除的用户
	 * @param email
	 * @param pmiId
	 * @return
	 */
	public UserBase getUserPmiByEmail(String pmiId);
	
	
	/**
	 * 获取时间段内改用户
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int findHostExtraPort(int hostId,Date startDate,Date endDate);
}
