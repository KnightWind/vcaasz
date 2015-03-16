package com.bizconf.vcaasz.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.SMSInfo;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.SMSService;

/**
 * @desc 
 * @author Administrator
 * @date 2014-9-28
 */
@Service
public class SMSServiceImpl extends BaseService implements SMSService {

	@Override
	public boolean saveSMSInfo(UserBase currentUser, ConfBase confInfo,
			String string, String sendUserNO) {
		
		SMSInfo smsInfo = new SMSInfo();
		smsInfo.setConfBaseId(confInfo.getId());
		smsInfo.setUserBaseId(currentUser.getId());
		smsInfo.setSmsContent(string);
		smsInfo.setSendNum(sendUserNO);
		smsInfo.setSendTime(new Date());
		smsInfo.setSendFlag(new Integer(1));
		smsInfo.setRetryCount(new Integer(10));
		
		try {
			if(libernate.saveEntity(smsInfo)!=null) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
