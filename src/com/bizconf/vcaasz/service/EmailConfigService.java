package com.bizconf.vcaasz.service;

import com.bizconf.vcaasz.entity.EmailConfig;
import com.bizconf.vcaasz.entity.UserBase;

public interface EmailConfigService {
	
	/**
	 * 系统管理员保存邮箱服务器设置
	 * @param emailConfig
	 * @return
	 */
	public EmailConfig saveSysConfig(EmailConfig emailConfig);
	
	/**
	 * 站点管理员保存邮件设置
	 * @param emailConfig
	 * @return
	 */
	public  EmailConfig saveSiteConfig(EmailConfig emailConfig);
	
	/**
	 * 站点管理员修改邮箱服务器设置
	 * @param emailConfig
	 * @return
	 */
	public boolean updateSiteConfig(EmailConfig emailConfig);
	
	
	/**
	 * 根据站点ID号获取邮箱HOST配置信息
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public EmailConfig getConfigBySiteId(Integer siteId)throws Exception;
	
	/**
	 * 获取系统邮件默认配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public EmailConfig getEmailSysConfig()throws Exception;
	
	
	/**
	 * 根据站点ID号删除邮箱服务器设置
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteConfigBySiteId(Integer siteId,UserBase delUser) throws Exception;
	
	
	

	
	/**
	 * 复制默认邮箱HOST配置，用于开通新站点或者是恢复某站点下的默认配置使用
	 * 
	 * @param toSiteId
	 * @return
	 * @throws Exception
	 */
	public boolean copyConfigFromDefault(Integer toSiteId)throws Exception;
	
	
	/**
	 * 比较两个EmailConfig是否一致,用于站点管理员修改邮箱配置是否与系统默认一致 
	 * 
	 * @param configa
	 * @param configb
	 * @return
	 * @throws Exception
	 */
	public boolean equalsConfig(EmailConfig configa,EmailConfig configb)throws Exception;
	
	

	/**
	 * 验证邮箱服务器设置是否一致
	 * @param body
	 * @param email
	 * @return
	 */
	public boolean testConfig(String body, String email);
	
	
	/**
	 *  获取站点自己设定的邮件配置
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public EmailConfig getSiteConfig(Integer siteId) throws Exception;
//	/**
//	 * 保存邮箱配置信息，包括增加、修改
//	 * 
//	 * */
//	public boolean setConfig(EmailConfig emailConfig) throws Exception;
	
//	/**
//	 * 删除邮箱HOST配置信息
//	 * 
//	 * */
//	public boolean deleteConfigByEmailConfig(EmailConfig emailConfig) throws Exception;
	
//	/**
//	 * 根据站点ID号删除邮箱HOST配置信息
//	 * */
//	public boolean deleteConfigById(Integer id) throws Exception;
	
//	/**
//	 * 根据主健ID号获取邮箱HOST配置信息
//	 * */
//	//public EmailConfig getEmailConfigById(Integer id)throws Exception;
	


	
	
	

	
	
}
