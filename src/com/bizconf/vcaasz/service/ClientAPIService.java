package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.JoinRandom;
import com.bizconf.vcaasz.entity.UserBase;

/**
 * 
 * 会议client接口服务
 * 
 * @author Chris Gao
 * 
 */
public interface ClientAPIService {

	/**
	 * 生成启动程序名所需要的参数后缀
	 * 
	 * @param conf 要加入的会议对象
	 * @param user 当前要加入的用户，如果是匿名用户，请构造，至少包含clientName和email
	 * @return
	 */
	public String makeSuffixForSetup(JoinRandom random);

	/**
	 * 生成小参数
	 * 
	 * @param conf 要加入的会议对象
	 * @param user 当前要加入会议的用户。如果是匿名用户，请构造，至少包含userName和email
	 * @return
	 */
	public String makePreParam(JoinRandom random,String ip);
	
	/**
	 * 根据random获取小参数，主要给setup调用
	 * @param random
	 * @return
	 */
	public String getPreParam(long random,String ip);

	/**
	 * 获取客户端启动最终所需要的参数
	 * 
	 * @param random
	 * @return
	 */
	public String getClientParam(long random);

	/**
	 * Client邀请用户加入
	 * 
	 * @param email
	 * @param content
	 * @param confId
	 * @param confPasswd
	 * @param lang
	 * @param userName
	 */
	public void clientInvite(String email, String content, int confId,
			String confPasswd, String lang, String userName);
	
	/**
	 * 结束一个会议时调用
	 * 
	 * @param confId 会议ID
	 */
	public void termConf(int confId);
	
	/**
	 * 获取一个Random
	 * @param randomId
	 * @return
	 */
	public JoinRandom getJoinRandomById(int randomId);
	
	/**
	 * 新增一理JoinRandom
	 * @param random
	 * @return
	 */
	public JoinRandom saveRandom(JoinRandom random);
	
	
	/**
	 * 根据企业用户生成企业通讯录xml
	 * @param users
	 * @return
	 */
	public String transUserToXml(List<UserBase> users,String retCode, String retContext);
}
