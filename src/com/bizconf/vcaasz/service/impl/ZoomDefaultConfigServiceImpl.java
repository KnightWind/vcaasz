package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.entity.ZoomDefaultConfig;
import com.bizconf.vcaasz.service.ZoomDefaultConfigService;

/** 
 *   
 * @package com.bizconf.vcaasz.service.impl 
 * @description: TODO
 * @author Martin
 * @date 2014年6月18日 上午10:06:44 
 * @version 
 */
@Service
public class ZoomDefaultConfigServiceImpl extends BaseService implements
		ZoomDefaultConfigService {

	@Override
	public boolean saveOrUpdateConfig(ZoomDefaultConfig config) {
//		if(!checkSaveable(config)) return false;
		try{
			if(config.getId()>0){
				libernate.updateEntity(config);
			}else{
				libernate.saveEntity(config);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	private boolean checkSaveable(ZoomDefaultConfig config){
		
		if(config == null){ return false;}
		
		if(config.getUserId()<1) return false;
		
		 
		if(config.getOptionStartType()>2) return false;
		
		return true;
	}
	@Override
	public ZoomDefaultConfig getUserDefaultConfig(int userId) {
		String sql = "select * from t_zoom_default_config where user_id = ?";
		ZoomDefaultConfig config = null;
		try {
			config = libernate.getEntityCustomized(ZoomDefaultConfig.class, sql, new Object[]{userId});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(config == null){
			config = new ZoomDefaultConfig();
		}
		return config;
	}
	
}
