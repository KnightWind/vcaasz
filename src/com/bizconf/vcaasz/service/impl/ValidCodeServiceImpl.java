package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.ValidCode;
import com.bizconf.vcaasz.entity.ValidateUserTime;
import com.bizconf.vcaasz.service.ValidCodeService;
import com.bizconf.vcaasz.util.PasswordUrlUtil;

@Service
public class ValidCodeServiceImpl extends BaseService implements ValidCodeService {

	@Override
	public boolean checkValidCode(String random, String type, String input) {
		String sessionId = LanguageHolder.getTSessionID();
		if (sessionId == null || sessionId.length() == 0) {
			return false;
		}
//		e42153e5-9e64-4fe9-a4ec-27da64e8b15c.syslogin.1362548177503
		String codeKey = genCodeKey(sessionId, type, random);
		try {
			ValidCode code = libernate.getEntity(ValidCode.class, codeKey);
			if (code == null) {
				return false;
			}
			
			if (!code.getCode().equalsIgnoreCase(input)) {
				return false;
			}
			
			this.remove(code);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void recordValidCode(String random, String type, String code) {
		String codeKey = this.genCodeKey(LanguageHolder.getTSessionID(), type, random);
		try {
			ValidCode checkCode = libernate.getEntity(ValidCode.class, codeKey);
			if (checkCode != null) {
				checkCode.setCode(code);
				checkCode.setCreateTime(new Date());
				libernate.updateEntity(checkCode);
				return;
			}
			
			//create new code
			ValidCode validCode = new ValidCode();
			validCode.setCreateTime(new Date());
			validCode.setCode(code);
			validCode.setCodeKey(codeKey);
			libernate.saveEntity(validCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(ValidCode code) {
		try {
			libernate.deleteEntity(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String genCodeKey(String tsessionId, String type, String random) {
		return tsessionId + "." + type + "." + random;
	}

	@Override
	public String getResetPassUrlForUser(UserBase userBase, String domain) {
		
		long ts = System.currentTimeMillis();
		
		try {
			StringBuffer buffer = new StringBuffer("select * from t_validate_user_time where login_email = ? order by id desc");
			ValidateUserTime userTime = libernate.getEntityCustomized(ValidateUserTime.class, buffer.toString(), new Object[]{userBase.getUserEmail()});
			if(userTime==null){
				ValidateUserTime validateUserTime = new ValidateUserTime();
				validateUserTime.setLoginEmail(userBase.getUserEmail());
				validateUserTime.setTime(ts);
				libernate.saveEntity(validateUserTime);//将生成重置url的时间保存到数据库中
			}else{
				userTime.setLoginEmail(userBase.getUserEmail());
				userTime.setTime(ts);
				libernate.updateEntity(userTime, "loginEmail","time");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return PasswordUrlUtil.getResetPasswordUrlForUser(userBase,domain,ts);
	}

	@Override
	public boolean isExpireTimeStamp(UserBase userBase, String ts) {
		
		StringBuffer buffer = new StringBuffer("select * from t_validate_user_time where login_email = ? order by id desc");
		try {
			ValidateUserTime validateUserTime = libernate.getEntityCustomized(ValidateUserTime.class, buffer.toString(), new Object[]{userBase.getUserEmail()});
			if(validateUserTime!=null && ts!=null){
				//链接中的时间戳与数据库中存在的时间戳不一致
				//密码过期
				if(!ts.equals(validateUserTime.getTime()) && PasswordUrlUtil.isExpireTimeStamp(ts)){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
