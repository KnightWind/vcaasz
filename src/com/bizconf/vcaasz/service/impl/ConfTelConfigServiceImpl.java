package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.ConfTelConfig;
import com.bizconf.vcaasz.service.ConfTelConfigService;
import com.bizconf.vcaasz.util.DateUtil;

@Service
public class ConfTelConfigServiceImpl extends BaseService implements ConfTelConfigService {

	@Override
	public ConfTelConfig getConfigById(Integer id) {
		ConfTelConfig config=null;
		try {
			config=libernate.getEntity(ConfTelConfig.class,id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return config;
	}

	@Override
	public ConfTelConfig save(ConfTelConfig confTelConfig) {
		// TODO Auto-generated method stub
		ConfTelConfig savedConfig=null;
		try {
			savedConfig=libernate.saveEntity(confTelConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savedConfig;
	}

	@Override
	public ConfTelConfig save(Integer siteId, Integer userId,
			Integer allowTelFlag, Integer autoCallFlag, Integer voipFlag) {
		ConfTelConfig config=new ConfTelConfig();
		if((siteId!=null && siteId.intValue()>0) || (userId!=null && userId.intValue()>0)){
			if(siteId==null){
				siteId=0;
			}
			if(userId==null){
				userId=0;
			}
			if(allowTelFlag==null){
				allowTelFlag=0;
			}
			if(autoCallFlag==null){
				autoCallFlag=0;
			}
			if(voipFlag==null){
				voipFlag=0;
			}
			config.setSiteId(siteId);
			config.setUserId(userId);
			config.setAllowTelFlag(allowTelFlag);
			config.setAutoCallFlag(autoCallFlag);
			config.setVoipFlag(voipFlag);
			config.setLastModifyTime(DateUtil.getGmtDate(null));
			try {
				config=libernate.saveEntity(config);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return config;
	}

	@Override
	public ConfTelConfig modify(ConfTelConfig confTelConfig) {
		ConfTelConfig savedConfig=null;
		try {
			savedConfig=libernate.updateEntity(confTelConfig,"id","allowTelFlag","autoCallFlag","voipFlag");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedConfig;
	}

	@Override
	public ConfTelConfig modify(Integer id,Integer allowTelFlag, Integer autoCallFlag, Integer voipFlag) {
		ConfTelConfig config=new ConfTelConfig();
		if(id!=null && id.intValue()>0 ){
			if(allowTelFlag==null){
				allowTelFlag=0;
			}
			if(autoCallFlag==null){
				autoCallFlag=0;
			}
			if(voipFlag==null){
				voipFlag=0;
			}
			config.setAllowTelFlag(allowTelFlag);
			config.setAutoCallFlag(autoCallFlag);
			config.setVoipFlag(voipFlag);
			config.setLastModifyTime(DateUtil.getGmtDate(null));
			try {
				config=libernate.updateEntity(config,"id","allowTeleFlag","autoCallFlag","voipFlag");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return config;
	}

	@Override
	public ConfTelConfig getSiteTelConfigBySiteId(Integer siteId) {
		ConfTelConfig siteConfig=null;
		if(siteId!=null && siteId.intValue() > 0){
			String configSql="select tc.* from t_conf_tel_config tc where tc.site_id=? and tc.user_id=0 and id >0";
			Object[] values=new Object[1];
			values[0]=siteId;
			try {
				siteConfig=libernate.getEntityCustomized(ConfTelConfig.class, configSql, values);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				configSql = null;
				values = null;
			}
		}
		return siteConfig;
	}

	@Override
	public ConfTelConfig getUserTelConfigByUser(Integer userId) {
		ConfTelConfig userConfig=null;
		if(userId!=null && userId.intValue() > 0){
			String configSql="select tc.* from t_conf_tel_config tc where tc.site_id > 0  and (tc.user_id=? or tc.user_id=0 ) and id >0";
			Object[] values=new Object[1];
			values[0]=userId;
			List<ConfTelConfig> tmpList=null;
//			ConfTelConfig eachConfig=null;
			try {
				tmpList=libernate.getEntityListBase(ConfTelConfig.class, configSql, values);//'.getEntityCustomized(ConfTelConfig.class, configSql, values);
				if(tmpList!=null && tmpList.size()>0){
					for(ConfTelConfig eachConfig: tmpList){
						if(eachConfig!=null && eachConfig.getId() >0){
							if(eachConfig.getSiteId() >0 && eachConfig.getUserId()==0 && eachConfig.getAllowTelFlag()==ConfConstant.CONF_CONFIG_ALLOW_PHONE_DISABLED){
								userConfig=new ConfTelConfig();
								userConfig.setAllowTelFlag(ConfConstant.CONF_CONFIG_ALLOW_PHONE_DISABLED);
								userConfig.setAutoCallFlag(ConfConstant.CONF_CONFIG_AUTO_CALL_DISABLED);
								userConfig.setVoipFlag(ConfConstant.CONF_CONFIG_VOIP_DISABLED);
								break;
							}
							
							if(eachConfig.getSiteId() >0 && eachConfig.getUserId()==0){
								userConfig=eachConfig;
							}
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				configSql = null;
				values = null;
				tmpList=null;
			}
			
		}
		return userConfig;
	}

	@Override
	public ConfTelConfig getUserTelConfigForEditConf(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfTelConfig> getUserConfigListByUserIds(Integer[] userIds) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
}
