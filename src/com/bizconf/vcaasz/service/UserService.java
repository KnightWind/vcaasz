package com.bizconf.vcaasz.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bizconf.vcaasz.entity.Condition;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;

public interface UserService {
	
	public void updateLastLoginDate(int userId);
	/**
	 *  更新用户的zoomtoken
	 * @param user
	 * @return
	 */
	public boolean updateUserZoomToken(UserBase user);
	
	/**
	 * 更新用户的zoom PMI id
	 * @param user
	 * @return
	 */
	public boolean updateUserPMI(UserBase user);
	
	/**
	 * 保存用户基本信息，包括添加、修改
	 * 
	 * @param userBase
	 * @return
	 * @throws Exception
	 */
	public boolean saveUserBase(UserBase userBase);
	
	/**
	 * 根据用户的ID号获取用户信息
	 * @param userId
	 * @return
	 */
	public UserBase getUserBaseById(Integer userId);
	
	/**
	 * 根据用户的ID号删除用户信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUserBaseById(Integer id);
	

	/**
	 * 根据用户的ID号锁定用户基本信息
	 * <p>
	 * 
	 * 1、Portal上锁定用户账号，使其为锁定状态，密码保持不变<br>
	 * 2、zoom账号将密码修改掉，改掉密码的算法自行设计，但要保证不可推算出
	 * 
	 * @author Chris Gao [gaohl81@gmail.com]
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean lockUserBaseById(Integer id);


	/**
	 * 根据用户的ID号锁定用户基本信息
	 * 
	 * <p>
	 * 1、portal上用户状态改为正常<br>
	 * 2、将zoom账号密码改为portal.db中的密码即可<br>
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean unlockUserBaseById(Integer id);
	

	/**
	 * 根据用户的ID号数组删除用户基本信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUserBaseByIds(Integer...ids);
	

	/**
	 * 根据用户的ID号数组锁定用户
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean lockUserBaseByIds(Integer...ids);


	/**
	 * 根据用户的ID号数组解锁用户
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean unlockUserBaseByIds(Integer...ids);
	
	
	/**
	 * 从上传的Excel文件中导入用户信息
	 * 
	 * @param excelFileName
	 * @return
	 * @throws Exception
	 */
	public boolean importUserByExcelFileName(String excelFileName);
	
	
	
	/**
	 * 根据登录名、用户名、或者是邮箱查询用户列表
	 * @param nameOrEmail
	 * @param sortField
	 * @param sortord
	 * @param pageModel
	 * @return
	 */
	public List<UserBase> getUserListByNameOrEmail(String nameOrEmail,String sortField, String sortord, PageModel pageModel);
	
	
	/**
	 * 根据登录名、用户名、或者是邮箱查询用户总数
	 * @param nameOrEmail
	 * @return
	 */
	public List<UserBase> getUserListByNameOrEmail(String nameOrEmail);
	
	
	
	
	/**
	 * 获取当前登录用户信息
	 * 
	 * @param request
	 * @return
	 */
	public UserBase getCurrentUser(HttpServletRequest request);
	
	/**
	 * 获取当前登录的企业管理员用户
	 * 
	 * @param request
	 * @return
	 */
	public UserBase getCurrentSiteAdmin(HttpServletRequest request);
	
	/**
	 * 获取当前系统管理员用户
	 * 
	 * @param request
	 * @return
	 */
	public SystemUser getCurrentSysAdmin(HttpServletRequest request);
	
	
	/**
	 * 根据系统管理员的ID号获取系统管理员信息
	 * @param sysUserId
	 * @return
	 */
	public SystemUser getSystemUserById(Integer sysUserId);
	
	
	/**
	 * 根据系统用户的ID号数组获取系统用户列表
	 * @param ids
	 * @return
	 */
	public List<SystemUser> getSysUserListByIds(Integer[] ids);
	
	
	/**
	 * 根据 站点Id号获取站点主管理员数据
	 * @param siteId
	 * @return
	 */
	public UserBase getSiteSupperMasterBySiteId(Integer siteId);
	
	/**
	 * 根据 站点ID号集合获取所有站点主管理员列表
	 * @param siteIds
	 * @return
	 */
	public List<UserBase> getSiteSupperMasterBySiteIdArray(Integer[] siteIds);

	
	/**
	 * 根据用户id集合获取用户列表对象
	 * 主要用于日志查询功能
	 * 可以查询已删除的用户信息
	 * shhc
	 * 2013-2-27
	 */
	public List<UserBase> getUserListByUserIdArray(Integer[] userIds);
	
	/**
	 * 根据登录名获取指定站点下的企业管理员
	 * wangyong
	 * 2013-1-31
	 */
	public UserBase getSiteAdminByLoginName(Integer siteId, String loginName);
	
	/**
	 * 根据登录名获取指定站点下的用户
	 * wangyong
	 * 2013-1-31
	 */
	public UserBase getSiteUserByLoginName(Integer siteId, String loginName);
	
	/**
	 * 根据登录名或者邮箱获取该站点下的用户
	 * @param siteId
	 * @param loginName
	 * @return
	 */
	public UserBase getSiteUserByLoginNameOrEmail(Integer siteId, String emailOrName);
	
	/**
	 * 根据邮箱名获取指定站点下的用户
	 * wangyong
	 * 2013-1-31
	 */
	public UserBase getSiteUserByEmail(Integer siteId, String email);
	
	/**
	 * 根据邮箱获取站点下的管理员或超级管理员信息
	 * @param siteId
	 * @param email
	 * @return
	 */
	public UserBase getSiteAdminByEmail(Integer siteId, String email);
	
	/**
	 * 统计系统管理员总记录数
	 * wangyong
	 * 2013-2-5
	 */
	public int CountSystemUser(Condition condition);
	
	/**
	 * 根据条件获取系统管理员列表
	 * wangyong
	 * 2013-2-5
	 */
	public List<SystemUser> getSystemUserList(Condition condition, PageModel pageModel);
	
	/**
	 * 新建系统管理员
	 * wangyong
	 * 2013-2-5
	 */
	public SystemUser createSystemUser(SystemUser systemUser);
	
	/**
	 * 删除系统管理员
	 * wangyong
	 * 2013-2-5
	 */
	public boolean delSystemUser(int id, SystemUser currentSystemUser);
	
	/**
	 * 修改系统管理员
	 * wangyong
	 * 2013-2-5
	 */
	public SystemUser updateSystemUser(SystemUser systemUser);
	
	
	public boolean updatePassWord(SystemUser systemUser);
	
	
	public boolean updateUserPassWord(UserBase userBase,String pwd);
	
	/**
	 * 根据系统管理员登录名获取系统管理员信息
	 * wangyong
	 * 2013-2-5
	 */
	public SystemUser getSystemUserByLoginName(String loginName);
	
	/**
	 * 根据系统管理员邮箱获取系统管理员信息
	 * wangyong
	 * 2013-2-5
	 */
	public SystemUser getSystemUserByEmail(String userEmail);
	
	
	/**
	 * 是否能够保存
	 * @param user
	 * @return
	 */
	public boolean siteUserSaveable(UserBase user);
//	
//	/**
//	 * 根据条件获取用户列表
//	 * */
//	public List<UserBase> getUserBaseListByCondition(Condition condition, PageModel pageModel)throws Exception;
//	
//	
//	/**
//	 * 根据条件统计用户总数
//	 * */
//	public List<UserBase> countUserBaseByCondition(Condition condition)throws Exception;
	public UserBase getUserBaseByZoomUserId(String hostId);
	
	
	
	/**
	 * 获取用户的永久会议室
	 * @param hostId
	 * @return
	 */
	public ConfBase getUserMeetingRoom(UserBase user);
	
	
	
	/**
	 * 从数据库查询个人永久会议室
	 * @param user
	 * @return
	 */
	public ConfBase getUserPMIformDB(UserBase user);

	/**
	 * 获取指定站点下的主持人列表
	 *	@param id
	 * 	@return
	 * */
	public List<UserBase> getSiteUserBySiteId(Integer id);

	public List<UserBase> exportHosts(String keyword, Object object,
			Integer siteId, Integer id);
	
	
	/**
	 * 保存或者更新系统用户
	 * @return
	 */
	public boolean saveOrUpdateSysUser(SystemUser sysUser);
	
	
	/**
	 * 获取通过用户手机获取用户
	 * @param siteId
	 * @param mobile
	 * @return
	 */
	public UserBase getSiteUserByMobile(String mobile);
	
	/**
	 * 锁定用户后，对用户的会议处理
	 * @param user
	 * @return
	 */
	public boolean lockUserMeeting(UserBase user);
	
	/**
	 * 解锁用户后对用户会议处理
	 * @param user
	 * @return
	 */
	public boolean unLockUserMeeting(UserBase user);
	
	
	
	/**
	 * 获取所有已过失效时间的用户
	 * @return
	 */
	public List<UserBase> findExpireUsers();
	
	
	/**
	 * 通过email获取用户信息
	 * @param email
	 * @return
	 */
	public UserBase getSiteUserByEmail(String email);
	public List<UserBase> getuserBaseListForFixedPort(SiteBase currentSite,
			Date startDate, Date endDate, String portnums);
}
