package com.bizconf.vcaasz.service;

import java.util.List;
import java.util.Map;

import com.bizconf.vcaasz.entity.EmpowerConfig;
import com.bizconf.vcaasz.entity.UserBase;

public interface EmpowerConfigService  {
	
	/**
	 * 创建授权
	 * @param empowerConfig
	 * @return
	 */
	public boolean saveEmpowerConfig(EmpowerConfig empowerConfig);
	

	/**
	 * 修改授权
	 * @param empowerConfig
	 * @return
	 */
	public boolean updateEmpowerConfig(EmpowerConfig empowerConfig);
	
	/**
	 * 根据站点ID号取站点的授权配置
	 * @param siteId
	 * @return
	 */
	public EmpowerConfig getSiteEmpowerConfigBySiteId(Integer siteId);
	
	
	/**
	 * 根据用户的ID号取用户的授权配置
	 * @param userId
	 * @return
	 */
	public EmpowerConfig getUserEmpowerConfigByUserId(Integer userId);
	
	
	/**
	 * 根据用户的ID号集合取用户的授权配置列表
	 * @param userIds
	 * @return
	 */
	public List<EmpowerConfig> getEmpowerListByUserIds(Integer[] userIds);
	
	
	
	
	/**
	 * 根据站点ID号取站点的全局变量
	 * @param siteId
	 * @return
	 */
	public EmpowerConfig getSiteEmpowerGlobalBySiteId(Integer siteId);
	
	
	/**
	 * 根据站点ID取站点的可应用的全限
	 * @param siteId
	 * @return
	 */
	public EmpowerConfig getSiteApplyEmpowerBySiteId(Integer siteId);
	
	
	/**
	 * 生成创建用户时的配置仅限
	 * @param sitePower
	 * @param userPower
	 * @return
	 */
	public EmpowerConfig makeEmpowerForUser(EmpowerConfig sitePower,EmpowerConfig userPower);
	
	/**
	 * 生成创建会议的权限配置
	 * */
	public EmpowerConfig makeEmpowerForConf(UserBase userBase);
	
	/**
	 * 创建站点的会议功能的全局变量
	 * @param empowerConfig
	 * @return
	 */
	public boolean saveSiteEmpowerGlobal(EmpowerConfig empowerConfig); 
	
	/**
	 * 更新站点的会议功能的全局变量
	 * @param empowerConfig
	 * @return
	 */
	public boolean updateSiteEmpowerGlobal(EmpowerConfig empowerConfig); 
	
	/**
	 * 设置功能
	 * @param config
	 * @param globleConfig
	 */
	public void setConfigValue(EmpowerConfig config,EmpowerConfig globleConfig);
	
	
	/**
	 * 获取到每个用户的功能授权信息
	 * @param users
	 * @return
	 */
	Map<Integer, EmpowerConfig> getUsersPermissions(List<UserBase> users);
}
