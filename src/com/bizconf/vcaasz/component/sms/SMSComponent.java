package com.bizconf.vcaasz.component.sms;

import java.util.List;
import java.util.Map;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.UserBase;

/**
 * 上海会畅通讯股份有限公司
 * @desc 短信通知
 * @author Darren
 * @date 2014-9-3
 */

public interface SMSComponent {

	/**
	 * 取消会议发送短信提醒
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * */
	public Map<String, String> sendCancleConfMsgToUser(ConfBase confInfo,
			String sendUserNO, UserBase currentUser);
	
	/**
	 * 发送短信邀请
	 * @param users 	被邀请参会者
	 * @param confInfo	会议信息
	 * @param sendType	被邀请出席的会议类型 正在进行 =1、预约的普通和周期会议=2  和修改会议发送短信（普通和周期【本次和全部】）=3
	 * @param currentUserBase 当前发送邀请主持人
	 * */
	public Map<String, String> sendMsgInviteToConfUser(List<ConfUser> users, ConfBase confInfo, Integer sendType, UserBase currentUserBase);

	/**
	 * 正在进行的会议发送邀请
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * */
	public Map<String, String> sendMeetingMsgToUser(ConfBase confInfo, String sendUserNO,UserBase currentUser) ;
	/**
	 * 发送单次预约会议
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * */
	public Map<String, String> sendSingleBookConfMsgToUser(ConfBase confInfo,
			String sendUserNO, UserBase currentUser);
	/**
	 * 发送周期预约会议短信提醒
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * @param cycleMode 
	 * */
	public Map<String, String> sendCycleBookConfMsgToUser(ConfBase confInfo,
			String sendUserNO, UserBase currentUser, String cycleMode);
	
	/**
	 * 修改会议发送短信提醒
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * */
	public Map<String, String> sendUpdateConfMsgToUser(ConfBase confInfo,
			String sendUserNO, UserBase currentUser);
	
	/**
	 * 生成短信的短链接
	 * */
	public String getJionUrl(ConfBase conf , int type);
	
	public Map<String, String> sendMsgForUserByPhones(String msgContent,  String sendUserNO);
	
	public Map<String, String> sendMsgByGet(String reqURL);
	
	public Map<String, String> sendMsgByPost(String reqURL , Map<String, String> params);
}
