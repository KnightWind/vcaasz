package com.bizconf.vcaasz.service;

import com.bizconf.vcaasz.entity.UserBase;

/** 
 *   
 * @package com.bizconf.vcaasz.service 
 * @description: TODO
 * @author Martin
 * @date 2014年7月18日 上午10:43:06 
 * @version 
 */
public interface SyncConfService {
	
	/**
	 * 同步会议信息
	 * @param hostId
	 * @return
	 */
	public boolean syncHostConfs(UserBase host);
}
