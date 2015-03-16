package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.EmpowerConfig;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.UserBase;

/**
 * @desc 
 * @author Martin
 * @date 2013-2-17
 */
public interface EnterpriseAdminService {
	
	/**
	 * 通过关键字获取企业用户列表 
	 * @param keyword
	 * @param sortField 排序字段值
	 * @param sortRule 排序规则值
	 * @param pageModel 
	 * @return
	 */
	List<UserBase> getSiteUserBases(String keyword,String sortField,String sortRule,Integer siteId,Integer creator);
	
	/**
	 * 获取企业管理员分页列表
	 * @param keyword
	 * @param sortField
	 * @param sortRule
	 * @param pageNo
	 * @return
	 */
	PageBean<UserBase> getUserBases(String keyword,String sortField,String sortRule, UserBase userBase, Integer creator,String pageNo);
	
	/**
	 * 企业管理员管理
	 * @param keyword
	 * @param sortField
	 * @param sortRule
	 * @param siteId
	 * @param pageNo
	 * @return
	 */
	PageBean<UserBase> getSiteManagers(String keyword, String sortField,
			String sortRule, UserBase userBase, Integer creator, String pageNo);
	/**
	 * 获取企业管理员分页列表
	 * @param keyword
	 * @param sortField
	 * @param sortRule
	 * @param pageNo
	 * @return
	 */
	PageBean<UserBase> getUserBasesBySite(String keyword,Integer siteId,String pageNo);
	/**
	 * 批量删除
	 * @param ids
	 */
	boolean deleteUserBases(String[] ids,int delUserId);
	
	/**
	 * 删除某个ID对应的企业用户
	 * @param id
	 */
	boolean deleteUserBase(Integer id,int deleter);
	
	
	/**
	 * 批量更改用户状态
	 * @param userStatus
	 */
	boolean changeLockState(String[] ids,Integer userStatus)throws Exception;
	
	
	/**
	 * 修改用户信息
	 * @param userBase
	 */
	boolean updateUserBase(UserBase userBase);
	
	
	
	/**
	 * 保存用户信息
	 * @param userBase
	 */
	UserBase saveUserBase(UserBase userBase);
	
	/**
	 * 校验用户数据
	 * @param user
	 * @return
	 */
	public boolean validUserBase(UserBase user);
	
	/**
	 * 批量添加数据
	 * @param users
	 * @return
	 */
	public boolean batchAddUsers(List<UserBase> users,EmpowerConfig config);
	
	/**
	 *验证集合中的userBase loginName是否已经在数据库中存在
	 * @param users
	 * @param siteId
	 * @return
	 */
	public boolean validUserBaseBatch(List<UserBase> users,Integer siteId);
	
	/**
	 * 批量导入时用
	 * @param userBase
	 * @param userConfig
	 * @return
	 */
	public UserBase saveUserBaseForImport(UserBase userBase,EmpowerConfig userConfig);
	
	/**
	 * 获取所有企业用户分页列表
	 * @param keyword
	 * @param sortField
	 * @param sortRule
	 * @param pageNo
	 * @return
	 */
	PageBean<UserBase> getSiteUserBasesAll(String keyword,String sortField,String sortRule,int numPartis, int pageNo, int pageSize);
	
	/**
	 * 导出用户信息到EXCEL
	 * oustin_quan	
	 * 2014-08-20
	 */
	public List<UserBase> exportSiteUserBase(String keyword,int numPartis);
}
