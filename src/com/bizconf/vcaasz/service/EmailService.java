package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.EmailInfo;
import com.bizconf.vcaasz.entity.License;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;

public interface EmailService {
	
	/**
	 * 创建站点\修改站点发送邮件
	 * @param siteId
	 * @return
	 */
	//public boolean createSiteEmail(Integer siteId);
	
	public boolean createSiteEmail(SiteBase site,UserBase admin);
	
	/**
	 * 创建站点用户成功后，给站点管理员发送邮件
	 * @param user
	 * @return
	 */
	public boolean createSiteAdminEmail(UserBase user);
	
	/**
	 * 创建站点用户
	 * @param user
	 * @return
	 */
	public Boolean createSiteUser(UserBase user);
	
	/**
	 * 修改站点管理员
	 * @param user
	 * @return
	 */
	public boolean updateSiteAdmin(UserBase user);
	
	/**
	 * 修改站点用户成功后，给站点管理员发送邮件
	 * @param user
	 * @return
	 */
	public Boolean updateSiteUserEmail(UserBase user);
	
	
	
	/**
	 * 修改站点信息发送邮件
	 * @param siteId
	 * @return
	 */
	public boolean updateSiteEmail(SiteBase site,UserBase admin);
	
	
//	/**
//	 * 根据邮箱重设密码
//	 * @param email
//	 * @return
//	 */
//	public boolean resetPassWordWithEmail(String email);
//	
//	/**
//	 * 根据站点标识与登录名重调密码
//	 * @param siteSign
//	 * @param loginName
//	 * @return
//	 */
//	public boolean getPasswordEmailWithSignAndLoginName(String siteSign,String loginName);
//	
	
	
	
	
	
	
	
	/**
	 * 保存邮件信息 
	 * @param emailInfo
	 * @return
	 */
	public boolean saveEmailInfo(EmailInfo emailInfo);
	
	
	/**
	 * 根据邮件主健 获取未发送邮件列表
	 * @param startId
	 * @param limit
	 * @return
	 */
	public List<EmailInfo> getUnSendEmailListByStartId(Long startId,Integer limit);
	
	
	
	/**
	 * 更新发送成功的EMAIL状态
	 * @param id
	 * @return
	 */
	public boolean updateSucceedEmailById(Long id);
	
	
	/**
	 * 更新邮件没有发送成功的EMail信息
	 * @param id
	 * @return
	 */
	public boolean updateUnSucceedEmailById(Long id);
	
	/**
	 * 根据Email信息Id号发送邮件
	 * @param id
	 * @return
	 */
	public boolean send(Long id);
	
	/**
	 * 根据Email对象 发送邮件
	 * @param emailInfo
	 * @return
	 */
	public boolean send(EmailInfo emailInfo);
	
	/**
	 * 新建系统管理员成功后发送邮件
	 * wangyong
	 * 2013-2-5
	 */
	//public boolean createSystemUserEmail(int sysUserId);
	
	/**
	 * 创建系统管理员成功后的邮件
	 * @param sysUser
	 * @return
	 */
	public boolean createSystemUserEmail(SystemUser sysUser);
	/**
	 * 修改系统管理员成功后发送邮件
	 * wangyong
	 * 2013-2-5
	 */
	public boolean updateSystemUserEmail(SystemUser sysUser);
	
	/**
	 * 忘记密码发送邮件接口
	 * @param user
	 * @param url
	 */
	public void resetPasswordEmail(SystemUser user,String url);
	
	/**
	 * 站点管理员忘记密码
	 * @param user
	 * @param url
	 */
	public void resetPasswordEmail(UserBase user,String url) throws Exception;
	
	
	/**
	 * 发送会议邀请
	 * @param userBases
	 * @param confInfo
	 * @return
	 */
	public boolean sendConfInvite(List<UserBase> userBases,ConfBase confInfo);
	
	/**
	 * 创建会议成功后发送会议信息到主持人邮箱
	 * @param user
	 * @param url
	 */
	public boolean createConfEmail(ConfBase conf, ConfCycle confCycle, UserBase currentUser);

	/**
	 * 邀请联系人参会
	 * @param users
	 * @param confInfo
	 * @return
	 */
	boolean sendInviteToConfUser(List<ConfUser> users, ConfBase confInfo);
	
	
	/**
	 * 添加会议提醒
	 * @param user
	 * @param conf
	 * @return
	 */
	boolean sendConfRemindEmail(UserBase user,ConfBase conf);
	
	
	/**
	 * conference remind for anyone
	 * @param userName
	 * @param email
	 * @param conf
	 * @return
	 */
	boolean remindEmailForAnyOne(String userName,String email,ConfBase conf);
	
	
	/**
	 * 会议提醒
	 * @param confUser
	 * @param conf
	 * @return
	 */
	boolean confRemind(ConfUser confUser,ConfBase conf);
	/**
	 * 取消会议的邮件提醒
	 * @param confUsers
	 * @return
	 */
	boolean confCancelEmail(List<ConfUser> confUsers,ConfBase conf);
	
	/**
	 * 会议修改通知
	 * @param confUsers
	 * @param conf
	 * @return
	 */
	boolean confModifyEmail(List<ConfUser> confUsers,ConfBase conf);
	
	/**
	 * 创建namehost
	 * @param host
	 * @param licenses
	 * @return
	 */
	boolean createNameHost(UserBase host,List<License> licenses);
	
	/**
	 * update name host
	 * @param host
	 * @param licenses
	 * @return
	 */
	boolean updateNameHost(UserBase host,List<License> licenses);
	
	/**
	 * 站点过期提醒
	 * @param site
	 * @return
	 */
	public List<SiteBase> getExpireRemindSites();
	
	
	public List<SiteBase> getExpiredSites();
	
	
	public boolean sendSiteRmindEmail(SiteBase site);
	
	
	public boolean sendEmailForSiteRemind(SiteBase siteBase) throws Exception;
	
	
	public boolean sendSiteExpiredEmail(SiteBase site);
	
	
	/**
	 * 点击接受时发送的日历提醒邮件
	 * @param confUser
	 * @param conf
	 * @return
	 */
	//public boolean accptConfRemind(ConfUser confUser, ConfBase conf);
	
//	public String getJionUrl(ConfBase conf,int type);
	
	
	
	public EmailInfo getEmailInfoById(int id);

	/**
	 * 修改单次周期会议发送邮件
	 * */
	public boolean updateNowCycleConfEmail(List<ConfUser> users, ConfBase conf);
	
	/**
	 *	发送取消单次周期会议的通知邮件 
	 * */
	public boolean sendCancelSingleCycleConfEmail(List<ConfUser> confUsers,
			ConfBase clone);
	
}
