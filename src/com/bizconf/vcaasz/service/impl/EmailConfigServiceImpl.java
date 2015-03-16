package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.EmailConfig;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.EmailConfigService;
import com.bizconf.vcaasz.util.DateUtil;

@Service
public class EmailConfigServiceImpl extends BaseService implements EmailConfigService {
	
	private final Logger logger=Logger.getLogger(EmailConfigServiceImpl.class);

	@Override
	public EmailConfig saveSysConfig(EmailConfig emailConfig) {
		if (emailConfig != null) {
			Integer id=emailConfig.getId();
			try {
				if(id !=null && id.intValue()>0){//修改HOST
					EmailConfig orgConfig = libernate.getEntity(EmailConfig.class, id);
					orgConfig.setEmailHost(emailConfig.getEmailHost());
					orgConfig.setEmailSender(emailConfig.getEmailSender());
					orgConfig.setEmailName(emailConfig.getEmailName());
					orgConfig.setEmailAccount(emailConfig.getEmailAccount());
					if(emailConfig.getEmailPassword()!=null){
						orgConfig.setEmailPassword(emailConfig.getEmailPassword());
					}
					emailConfig=libernate.updateEntity(orgConfig);
				}else{//增加HOST
					emailConfig.setCreateTime(DateUtil.getGmtDate(null));
					emailConfig.setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
					emailConfig.setDelUser(0);
					emailConfig.setDelTime(DateUtil.getGmtDate(null));
					emailConfig=libernate.saveEntity(emailConfig);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		return emailConfig;
	}
	
	/**
	 * 保存站点
	 */
	public EmailConfig saveSiteConfig(EmailConfig emailConfig) {
		if (emailConfig != null) {
			Integer id=emailConfig.getId();
			try {
				if(id !=null && id.intValue()>0){//修改HOST
					EmailConfig orgConfig = libernate.getEntity(EmailConfig.class, id);
					orgConfig.setSiteId(emailConfig.getSiteId());
					orgConfig.setEmailHost(emailConfig.getEmailHost());
					orgConfig.setEmailSender(emailConfig.getEmailSender());
					orgConfig.setEmailName(emailConfig.getEmailName());
					orgConfig.setEmailAccount(emailConfig.getEmailAccount());
					if(emailConfig.getEmailPassword()!=null){
						orgConfig.setEmailPassword(emailConfig.getEmailPassword());
					}
					emailConfig=libernate.updateEntity(orgConfig);
				}else{//增加HOST
					emailConfig.setCreateTime(DateUtil.getGmtDate(null));
					emailConfig.setCreateUser(0);
					emailConfig.setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
					emailConfig.setDelUser(0);
					emailConfig.setDelTime(DateUtil.getGmtDate(null));
					emailConfig.setEmailVersion(1);
					emailConfig=libernate.saveEntity(emailConfig);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		return emailConfig;
	}

	@Override
	public boolean updateSiteConfig(EmailConfig emailConfig) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 根据某个站点获取该站点的邮件服务器配置， 如果该站点没有设置，则返回系统默认邮件设置
	 */
	@Override
	public EmailConfig getConfigBySiteId(Integer siteId) throws Exception {
		EmailConfig emailConfig = null;
		try {
			emailConfig = libernate
					.getEntityCustomized(EmailConfig.class,
							"select * from t_email_config where del_flag=? and site_id=?",
							new Object[] { ConstantUtil.DELFLAG_UNDELETE, siteId });
			if(emailConfig==null){
				emailConfig  = libernate
						.getEntityCustomized(EmailConfig.class,
								"select * from t_email_config where del_flag=? and site_id=?",
								new Object[] { ConstantUtil.DELFLAG_UNDELETE, 0 });
				emailConfig.setId(0);
			}
		} catch (SQLException e) {
			logger.error("get EmailConfig error", e);
		}
		return emailConfig;
	}
	
	public EmailConfig getSiteConfig(Integer siteId) throws Exception {
		EmailConfig emailConfig = null;
		try {
			emailConfig = libernate
					.getEntityCustomized(EmailConfig.class,
							"select * from t_email_config where del_flag=? and site_id=?",
							new Object[] { ConstantUtil.DELFLAG_UNDELETE, siteId });
		} catch (SQLException e) {
			logger.error("get EmailConfig error", e);
		}
		return emailConfig;
	}

	@Override
	public EmailConfig getEmailSysConfig() throws Exception {
		EmailConfig emailConfig = null;
		try {
			emailConfig = libernate
					.getEntityCustomized(EmailConfig.class,
							"select * from t_email_config where del_flag=? and site_id=?",
							new Object[] { ConstantUtil.DELFLAG_UNDELETE, 0 });
		} catch (SQLException e) {
			logger.error("get EmailConfig error", e);
		}
		return emailConfig;

	}

	@Override
	public boolean deleteConfigBySiteId(Integer siteId ,UserBase delUser) throws Exception {
		boolean delStatus = false;
		if (siteId == null || siteId.intValue() <= 0) {
			return delStatus;
		}
		String delSql = "update  t_email_config set del_flag=?,del_user=?,del_time=? where  site_id=? and del_flag=? ";
		delStatus = libernate.executeSql(delSql, new Object[] { ConstantUtil.DELFLAG_DELETED,delUser.getId(),DateUtil.getGmtDate(null),siteId,ConstantUtil.DELFLAG_UNDELETE });
		return delStatus;
	}

	@Override
	public boolean copyConfigFromDefault(Integer toSiteId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equalsConfig(EmailConfig configa, EmailConfig configb)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean testConfig(String body, String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
