package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.EmailTemplate;
import com.bizconf.vcaasz.service.EmailTemplateService;
@Service
public class EmailTemplateServiceImpl extends BaseService implements EmailTemplateService {

	@Override
	public boolean saveSysTemplate(EmailTemplate template) {
		boolean saveStatus=false;
		if(template!=null){
			try {
				libernate.saveEntity(template);
				saveStatus=true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return saveStatus;
	}
	
	
	@Override
	public boolean updateSiteTemplate(EmailTemplate template) {
		// TODO Auto-generated method stub
		boolean saveFlag=false;
		if(template != null){
			//加后台验证功能 代码
			try {
				DAOProxy.getLibernate().updateEntity(template, "id", "email_content"); 
				saveFlag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saveFlag;
	}
	/**
	 * 按类型返回站点邮件模板
	 */
	@Override
	public EmailTemplate getTemplateBySiteId(Integer siteId, Integer tempType) {
		EmailTemplate emailTemplate=null;
		if(siteId!=null && siteId.intValue()>0 && tempType !=null && tempType.intValue() >0){
			StringBuffer condBuffer=new StringBuffer();
			Object[] values=new Object[3];
			try {
				condBuffer.append(" del_flag = ?  ");
				condBuffer.append(" and site_id = ?");
				condBuffer.append(" and email_type = ?");
				values[0] = ConstantUtil.DELFLAG_UNDELETE;
				values[1] = siteId;
				values[2] = tempType;
				
				emailTemplate = libernate.getEntityWithCondition(EmailTemplate.class, condBuffer.toString(), values);
				//如果该站点还没有相关模板，取系统模板
				if(emailTemplate==null){
					emailTemplate = getSysTemplateByType(tempType);
					emailTemplate.setId(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				condBuffer=null;
				values=null;
			}
		}
		return emailTemplate;
	}
	
	
	@Override
	public List<EmailTemplate> getTemplateListBySiteId(Integer siteId) {
		List<EmailTemplate> templateList=null;
		if (siteId != null && siteId.intValue() > 0) {
			StringBuffer condBuffer=new StringBuffer();
			Object[] values=new Object[2];
			try {

				condBuffer.append("del_flag = ?  ");
				condBuffer.append(" and site_id = ?");
				values[0] = ConstantUtil.DELFLAG_UNDELETE;
				values[1] = siteId;
				
				libernate.getEntityListWithCondition(condBuffer.toString(), values, EmailTemplate.class);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return templateList;
	}
	
	@Override
	public EmailTemplate getSysTemplateByType(Integer tempType) {
		EmailTemplate template = null;
		StringBuffer condBuffer=new StringBuffer();
		Object[] values=new Object[3];
		try {
			condBuffer.append("temp_version = ?  ");
			condBuffer.append(" and sys_reserve_flag = ?  ");
			condBuffer.append(" and email_type = ?");
			values[0] = ConstantUtil.SYS_VERSION; //系统模板 （区别于站点模板）
			values[1] = ConstantUtil.SYS_RESERVE_FLAG_N;//非系统保留版本
			values[2] = tempType;
			List<EmailTemplate> templates =  libernate.getEntityListWithCondition(condBuffer.toString(), values, EmailTemplate.class);
			if(templates!=null && templates.size()>0){
				template = templates.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return template;
	}

	@Override
	public boolean deleteTemplateBySiteId(Integer siteId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean copySiteTemplateFromDefaultAndType(Integer toSiteId,
			Integer tempType) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean copySiteTemplateFromDefault(Integer toSiteId)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equalsTemplate(EmailTemplate templatea,
			EmailTemplate templateb) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean updateTemplateContent(EmailTemplate template) {
		boolean saveFlag=false;
		if(template != null){
			//加后台验证功能 代码
			try {
				DAOProxy.getLibernate().updateEntity(template, "id", "email_content"); 
				saveFlag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saveFlag;
	}
	
	
	@Override
	public EmailTemplate getSysRecoverTemplateByType(Integer type) {
		EmailTemplate template = null;
		StringBuffer condBuffer=new StringBuffer();
		Object[] values=new Object[3];
		try {
			condBuffer.append("temp_version = ?  ");
			condBuffer.append(" and sys_reserve_flag = ?  ");
			condBuffer.append(" and email_type = ?");
			values[0] = ConstantUtil.SYS_VERSION; //系统模板 （区别于站点模板）
			values[1] = ConstantUtil.SYS_RESERVE_FLAG_Y;//系统保留版本 用于系统管理员恢复模板
			values[2] = type;
			List<EmailTemplate> templates = libernate.getEntityListWithCondition(condBuffer.toString(), values, EmailTemplate.class);
			if(templates.size()>0){
				template = templates.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return template;
	}


	@Override
	public boolean saveOrUpdateSiteTemplate(EmailTemplate template) {
		// TODO Auto-generated method stub
		if(template.getId()>0){
			return updateTemplateContent(template);
		}else{
			EmailTemplate newTemp = getTemplateBySiteId(template.getSiteId(),template.getEmailType());
			try {
				if(!newTemp.getSiteId().equals(template.getSiteId())){
					newTemp.setId(null);
					newTemp.setSiteId(template.getSiteId());
					newTemp.setCreateTime(new Date());
					newTemp.setEmailContent(template.getEmailContent());
					newTemp.setCreateUser(template.getCreateUser());
					newTemp.setTempVersion(1);
					libernate.saveEntity(newTemp);
				}else{
					newTemp.setEmailContent(template.getEmailContent());
					libernate.updateEntity(newTemp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
