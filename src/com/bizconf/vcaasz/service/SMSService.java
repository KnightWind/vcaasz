package com.bizconf.vcaasz.service;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.UserBase;

/**
 * @desc 
 * @author Administrator
 * @date 2014-9-28
 */
public interface SMSService {

	/**
	 * 保存短信邀请
	 * */
	public boolean saveSMSInfo(UserBase currentUser, ConfBase confInfo, String string,
			String sendUserNO);
	
}
