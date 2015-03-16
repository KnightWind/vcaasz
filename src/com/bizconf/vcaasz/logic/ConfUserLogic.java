package com.bizconf.vcaasz.logic;

import java.util.Collection;
import java.util.List;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.UserBase;

/**
 * 用户会议关系业务逻辑接口
 * 
 * @author Chris
 *
 */
public interface ConfUserLogic {
	
	public ConfUser addConfBaseUser(ConfBase conf, UserBase user,Integer remindFlag);
	/**
	 * 创建关系
	 * 
	 * @param conf
	 * @param user
	 * @return
	 */
	public boolean addConfUser(ConfBase conf, UserBase user,Integer remindFlag);
	
	/**
	 * 获取当前关系
	 * 
	 * @param confId
	 * @param userId
	 * @return
	 */
	public ConfUser getConfUser(int confId, int userId);
	
	public ConfUser getConfUser(int id);
	
	/**
	 * 更新当前关系
	 * 
	 * @param conf
	 * @param user
	 * @return
	 */
	public boolean updateConfUser(ConfBase conf, UserBase user);
	
	/**
	 * 解除用户会议关系
	 * 
	 * @param confId
	 * @param userId
	 * @return
	 */
	public boolean deleteConfUser(int confId, int userId);
	
	/**
	 * 解除用户与周期会议关系
	 */
	public boolean deleteConfUsersByCycleId(int cycleId);
	
	/**
	 * 解除用户会议关系
	 * @param confId
	 * @return
	 */
	public boolean deleteConfUsersByConfId(int confId);
	
	/**
	 * 通过邮件和名称获取关系
	 * @param confId
	 * @param userName
	 * @param email
	 * @return
	 */
	ConfUser getConfUserByEmailAndName(int confId, String userName, String email);
	
	/**
	 * 获取处理接受邀请的链接地址
	 * 
	 * @param confId
	 * @param userId
	 * @param userName
	 * @param userEmail
	 * @return
	 */
	public String retrieveAcceptURI(int confId, int userId, String userName, String userEmail);
	
	/**
	 * 获取处理拒绝邀请的链接地址
	 * 
	 * @param confId
	 * @param userId
	 * @param userName
	 * @param userEmail
	 * @return
	 */
	public String retrieveRefuseURI(int confId, int userId, String userName, String userEmail);
	
	
	/**
	 * 获取所有的会议人员
	 * @param confId
	 * @return
	 */
	List<ConfUser> getConfUserList(int confId);
	
	/**
	 * 获取会议主持人
	 * @param confId
	 * @return
	 */
	public ConfUser getConfUserHosterForPublicConf(int confId);
	/**
	 * 获取周期会议的邀请用户
	 * @param cycId
	 * @return
	 */
	Collection<ConfUser> getCycleConfUsers(int cycId);
}
