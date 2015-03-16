package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.EmailTemplate;

public interface EmailTemplateService {

	/**
	 * 系统管理员保存一份模板
	 * @param template
	 * @return
	 */
	public boolean saveSysTemplate(EmailTemplate template);
	
	/**
	 * 更新站点模板内容
	 * @param template
	 * @return
	 */
	public boolean updateSiteTemplate(EmailTemplate template);
	
	
	/**
	 * 系统管理员 更新系统模板内容
	 * @param template
	 * @return 
	 */
	public boolean updateTemplateContent(EmailTemplate template);
	
	
	/**
	 * 根据站点Id号现模板类别获取模板
	 * @param siteId
	 * @param tempType
	 * @return
	 */
	public EmailTemplate getTemplateBySiteId(Integer siteId,Integer tempType);
	
	/**
	 * 根据站点ID号获取邮件模板列表
	 * @param siteId
	 * @return
	 */
	public List<EmailTemplate> getTemplateListBySiteId(Integer siteId);
	
	
	/**
	 * 根据模板类别获取系统默认模板
	 * @param tempType
	 * @return
	 */
	public EmailTemplate getSysTemplateByType(Integer tempType);
	
	/**
	 * 根据站点Id号删除该站点下的所有模板
	 * @param siteId
	 * @return
	 */
	public boolean deleteTemplateBySiteId(Integer siteId);
	

	/**
	 * 复制邮件模板，用于开通新站点，恢复某站点下的默认默认使用
	 * 
	 * @param toSiteId
	 * @param tempType
	 * @return
	 * @throws Exception
	 */
	public boolean copySiteTemplateFromDefaultAndType(Integer toSiteId,Integer tempType)throws Exception;
	
	
	/**
	 * 复制邮件模板，用于开通新站点，恢复某站点下的默认使用
	 * 
	 * @param toSiteId
	 * @return
	 * @throws Exception
	 */
	public boolean copySiteTemplateFromDefault(Integer toSiteId)throws Exception;
	
	/**
	 * 比较两个EmailTemplate是否一致,用于站点管理员修改邮箱配置是否与系统默认一致 
	 * 
	 * @param templatea
	 * @param templateb
	 * @return
	 * @throws Exception
	 */
	public boolean equalsTemplate(EmailTemplate templatea,EmailTemplate templateb)throws Exception;
	
	
	/**
	 * 获取某个类型邮件模板的出厂保留模板
	 * @param type
	 * @return
	 */
	public EmailTemplate getSysRecoverTemplateByType(Integer type);
	
	/**
	 * 修改站点邮件模板内容
	 * @param template
	 * @return
	 */
	public boolean saveOrUpdateSiteTemplate(EmailTemplate template);
}
