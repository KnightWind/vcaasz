package com.bizconf.vcaasz.service;

import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.ValidCode;

/**
 * 验证码服务
 * 
 * @author Chris Gao
 *
 */
public interface ValidCodeService {
	
	public void recordValidCode(String random, String type, String code);
	
	/**
	 * 检查验证输入是否正确
	 * <p>
	 * 请求验证码，参考：/valid/image?type=TYPE&random=RANDOM
	 * 
	 * @param random 随机值，与请求验证码的时候一致
	 * @param type 验证码类型，与请验证码的时候一致
	 * @param input 当前用户的输入
	 * @return
	 */
	public boolean checkValidCode(String random, String type, String input);
	
	public void remove(ValidCode code);

	/**
	 * 生成重置密码的url
	 * */
	public String getResetPassUrlForUser(UserBase userBase, String domain);

	/**
	 * 校验登录用户的时间戳
	 * */
	public boolean isExpireTimeStamp(UserBase userBase, String ts);
}
