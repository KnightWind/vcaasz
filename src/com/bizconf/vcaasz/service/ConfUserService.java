package com.bizconf.vcaasz.service;

import java.util.Date;
import java.util.List;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.UserBase;

/**
 * 用户会议关系维护服务接口
 * <p>
 * 
 * @author Chris Gao
 *
 */
public interface ConfUserService {
	/**
	 * 创建会议完成后调用
	 * 
	 * @param conf
	 * @param hostUser
	 * @return
	 */
	public boolean fillConfUserForCreate(ConfBase conf, UserBase hostUser);
	
	/**
	 * 创建周期会议完成后调用
	 * <p>
	 * 
	 * @param confList
	 * @param hostUser
	 * @return
	 */
	public boolean fillConfUserForCreateCycle(List<ConfBase> confList, UserBase hostUser);
	
	/**
	 * 修改单个会议完成后调用
	 * <p>
	 * @param conf
	 * @param hostUser
	 * @return
	 */
	public boolean fillConfUserForModify(ConfBase conf, UserBase hostUser);
	
	/**
	 * 修改周期会议完成后调用
	 * <p>
	 * @param conf
	 * @param hostUser
	 * @return
	 */
	public boolean fillConfUserForModifyCycle(List<ConfBase> confList, UserBase hostUser);
	
	/**
	 * 取消单个会议调用
	 * <p>
	 * 
	 * @param conf
	 * @param confId
	 * @return
	 */
	public boolean fillConfUserForCancel(int confId, UserBase hostUser);
	
	/**
	 * 取消周期会议调用
	 * <p>
	 * 
	 * @param conf
	 * @param cycleId
	 * @return
	 */
	public boolean fillConfUserForCancelCycle(int cycleId, UserBase hostUser);
	
	/**
	 * 邀请用户调用
	 * 
	 * @param conf
	 * @param inviteUsers 被邀请的用户列表，如果是非注册用户，请构造：userId=0
	 * @return
	 */
	public boolean fillConfUserForInvite(ConfBase conf, List<UserBase> inviteUsers,Integer remindFlag);
	
	/**
	 * 会议状态更新后调用 [confTask]
	 * 
	 * @param confId
	 * @param confStatus
	 * @return
	 */
	public boolean fillConfUserForConfStatusUpdate(int confId, int confStatus);
	
	/**
	 * 获取某会议的邀请用户列表
	 * 
	 * @param confId
	 * @return
	 */
	public List<ConfUser> getConfInviteUserList(int confId);
	
	/**
	 * 获取某会议的所有参会用户
	 * @param confId
	 * @return
	 */
	public List<ConfUser> getAllConfUserList(int confId,int remindFlag);
	/**
	 * 接受邀请
	 * 
	 * @param confUserId
	 * @return
	 */
	public boolean recvInvite(int confUserId);
	
	/**
	 * 谢绝邀请
	 * 
	 * @param confUserId
	 * @return
	 */
	public boolean refuseInvite(int confUserId);
	
	/**
	 * 获取处理接受邀请的链接地址
	 * 
	 * @param confId
	 * @param userId
	 * @param userName
	 * @param userEmail
	 * @return
	 */
	public String retrieveAcceptURI(int confId, int userId, String userName, String userEmail);
	
	/**
	 * 获取处理拒绝邀请的链接地址
	 * 
	 * @param confId
	 * @param userId
	 * @param userName
	 * @param userEmail
	 * @return
	 */
	public String retrieveRefuseURI(int confId, int userId, String userName, String userEmail);
	
	
	/**
	 * 取出会议开始前
	 * @param aheadMinute
	 * @param scopeMinute
	 * @return
	 */
	public List<ConfUser> getConfUserListForRemind(Integer aheadMinute,Integer scopeMinute);
	
	
	/**
	 * 更新会议用户中的邮件提醒标志
	 * @param confUser
	 * @return
	 */
	public boolean updateRemindFlag(ConfUser confUser);
	

	/**
	 * 更新会议的开始时间
	 * @param confId
	 * @param date
	 * @return
	 */
	public boolean updateStartTime(Integer confId,Date startTime);
	
	/**
	 * 获取某会议的邀请用户列表(未应答的邀请用户列表)
	 * 
	 * @param confId
	 * @return
	 */
	public List<ConfUser> getNoResponseUserList(int confId);
	/*
	 * 补充创建无限期周期会议的下一条会议后，调用该方法，为新会议指定邀请参会人
	 *  1.创建无限期周期会议时只创建了30条会议，每当结束一条会议时，则调用该方法再补充创建一条会议，保证该无限期周期会议一直有30条
	 *  2.复制一份该周期会议的邀请用户到新会议
	 * wangyong
	 * 2013-8-22
	 */
	public boolean addInfiniteConfUser(ConfBase oldConf, ConfBase newConf);

	public List<ConfUser> fillConfUserForSMSInvite(ConfBase confInfo,
			List<UserBase> ubs, int i);

	public boolean delConfUserById(ConfUser confUser);
	
	
}
