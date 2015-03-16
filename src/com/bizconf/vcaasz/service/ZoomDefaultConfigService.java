package com.bizconf.vcaasz.service;

import com.bizconf.vcaasz.entity.ZoomDefaultConfig;

/** 
 *   
 * @package com.bizconf.vcaasz.service 
 * @description: TODO
 * @author Martin
 * @date 2014年6月17日 下午6:04:41 
 * @version 
 */
public interface ZoomDefaultConfigService {
	
	/**
	 * 保存或者修改默认会议设置
	 * @param config
	 * @return
	 */
	public boolean saveOrUpdateConfig(ZoomDefaultConfig config);
	
	
	/**
	 * 通过用户ID获取该用户的默认会议设置
	 * @param userId
	 * @return
	 */
	public ZoomDefaultConfig getUserDefaultConfig(int userId);
	
	
	
}
