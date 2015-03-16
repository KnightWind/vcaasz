package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.ConfTelConfig;

public interface ConfTelConfigService {

	/**
	 * 根据主键ID号获取配置信息
	 * @param id
	 * @return
	 */
	public ConfTelConfig getConfigById(Integer id);
	
	/**
	 * 根据对象保存数据
	 * @param confTeleConfig
	 * @return
	 */
	public ConfTelConfig save(ConfTelConfig confTelConfig);
	
	
	
	/**
	 * 根据参数属性保存数据
	 * @param siteId
	 * @param userId
	 * @param allowTeleFlag
	 * @param autoCallFlag
	 * @param voipFlag
	 * @return
	 */
	public ConfTelConfig save(Integer siteId,Integer userId,Integer allowTelFlag,Integer autoCallFlag,Integer voipFlag);
	
	/**
	 * 修改数据对象
	 * @param confTeleConfig
	 * @return
	 */
	public ConfTelConfig modify(ConfTelConfig confTelConfig);

	/**
	 * 根据属性修改数据
	 * @param siteId
	 * @param userId
	 * @param allowTeleFlag
	 * @param autoCallFlag
	 * @param voipFlag
	 * @return
	 */
	public ConfTelConfig modify(Integer id,Integer allowTelFlag, Integer autoCallFlag, Integer voipFlag);
	
	
	/**
	 * 根据站点ID号获取站点的管理员下的电话会议配置
	 * @param siteId
	 * @return
	 */
	public ConfTelConfig getSiteTelConfigBySiteId(Integer siteId);
	
	/**
	 * 获取有创建会议权限用户的电话会议的配置
	 * @param userId
	 * @return
	 */
	public ConfTelConfig getUserTelConfigByUser(Integer userId);
	
	
	/**
	 * 用户创建、修改会议时，取出用户电话会议的参数
	 * @param userId
	 * @return
	 */
	public ConfTelConfig getUserTelConfigForEditConf(Integer userId);
	
	
	
	public List<ConfTelConfig> getUserConfigListByUserIds(Integer[] userIds);
	
	
	
	
	
	
	
	
}
