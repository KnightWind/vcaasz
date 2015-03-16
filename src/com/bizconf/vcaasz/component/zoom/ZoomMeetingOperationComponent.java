package com.bizconf.vcaasz.component.zoom;

import java.util.List;
import java.util.Map;

import com.bizconf.vcaasz.entity.ConfBase;

/** 
 *   
 * @package com.bizconf.video.component.mcu 
 * @description: zoom 会议相关的操作接口
 * @author Martin
 * @date 2014年6月6日 上午9:57:15 
 * @version 
 */
public interface ZoomMeetingOperationComponent {
	
	
	
	/**
	 * 创建会议
	 * @param host_id 主持人ID
	 * @param topic 主题
	 * @param type 会议类型
	 * @param start_time 会议开始时间
	 * @param duration  会议时长
	 * @param timezone  时区
	 * @param password  密码
	 * @param option_jbh 是否在主持人之前可以入会
	 * @param option_start_type 会议类型   "video" or "screen_share".
	 * @return 创建会议的状态
	 */
	public Map<String, Object> createMeeting(String apiKey,String apiToken,String hostId,String topic,int type,
			String startTime,int duration,String timeZone,
			String password,boolean optionJbh,String optionStartType);
	
	/**
	 * 创建即时会议接口
	 * @param hostId
	 * @param topic
	 * @param password
	 * @param optionJbh
	 * @param optionStartType
	 * @return
	 */
	public Map<String, Object> createInstantMeeting(String apiKey,String apiToken,String hostId,String topic,
			String password,boolean optionJbh,String optionStartType);
	/**
	 * 创建即时会议接口
	 * @param hostId
	 * @param topic
	 * @param password
	 * @param optionJbh
	 * @param optionStartType
	 * @return
	 */
	public Map<String, Object> createInstantMeetingMA(String apiKey,String apiToken,String accountId,String hostId,
			String topic, String password, boolean optionJbh,
			String optionStartType);
	
	/**
	 * 创建预约会议接口通过MA方式
	 * @param accountId
	 * @param hostId
	 * @param topic
	 * @param startTime
	 * @param duration
	 * @param timeZone
	 * @param password
	 * @param optionJbh
	 * @param optionStartType
	 * @return
	 */
	public Map<String, Object> createScheduleMeeting(String apiKey,String apiToken,String hostId,String topic,
			String startTime,int duration,String timeZone,
			String password,boolean optionJbh,String optionStartType);
	
	/**
	 * 创建预约会议接口通过MA方式
	 * @param accountId
	 * @param hostId
	 * @param topic
	 * @param startTime
	 * @param duration
	 * @param timeZone
	 * @param password
	 * @param optionJbh
	 * @param optionStartType
	 * @return
	 */
	public Map<String, Object> createScheduleMeetingMA(String apiKey,String apiToken,String accountId,String hostId,String topic,
			String startTime,int duration,String timeZone,
			String password,boolean optionJbh,String optionStartType);
	
	/**
	 * 创建永久会议接口
	 * @param hostId
	 * @param topic
	 * @param password
	 * @param optionJbh
	 * @param optionStartType 会议类型   "video" or "screen_share".
	 * @return
	 */
	public Map<String, Object> createPermanentMeeting(String apiKey,String apiToken,String hostId,String topic,
			String password,boolean optionJbh,String optionStartType);
	/**
	 * 创建永久会议接口通过MA方式
	 * @param accountId
	 * @param hostId
	 * @param topic
	 * @param password
	 * @param optionJbh
	 * @param optionStartType 会议类型   "video" or "screen_share".
	 * @return
	 */
	public Map<String, Object> createPermanentMeetingMA(String apiKey,String apiToken,String accountId,String hostId,String topic,
			String password,boolean optionJbh,String optionStartType);
	
	
	/**
	 * 修改会议信息
	 * @param meetingId
	 * @param host_id
	 * @param topic
	 * @param type
	 * @param start_time
	 * @param duration
	 * @param timezone
	 * @param password
	 * @param option_jbh
	 * @param option_start_type 会议类型   "video" or "screen_share".
	 * @return 修改状态 
	 */
	public int updateMeeting(String apiKey,String apiToken,String meetingId,String hostId,String topic,
			String startTime,int duration,String timeZone,
			String password,boolean optionJbh,String optionStartType);
	/**
	 * 修改会议信息通过MA方式
	 * @param accountId
	 * @param meetingId
	 * @param host_id
	 * @param topic
	 * @param type
	 * @param start_time
	 * @param duration
	 * @param timezone
	 * @param password
	 * @param option_jbh
	 * @param option_start_type 会议类型   "video" or "screen_share".
	 * @return 修改状态 
	 */
	public int updateMeetingMA(String apiKey,String apiToken,String accountId,String meetingId,String hostId,String topic,
			String startTime,int duration,String timeZone,
			String password,boolean optionJbh,String optionStartType);
	/**
	 * 删除或者取消一个会议
	 * @param meetingId
	 * @param host_id
	 * @return 删除会议状态   
	 * 正在进行会议的delete
	 */
	public int deleteMeeting(String apiKey,String apiToken,String meetingId,String host_id);
	/**
	 * 删除或者取消一个会议通过MA方式
	 * @param accountId
	 * @param meetingId
	 * @param host_id
	 * @return 删除会议状态   
	 * 正在进行会议的delete
	 */
	public int deleteMeetingMA(String apiKey,String apiToken,String accountId,String meetingId,String host_id);
	
	
	/**
	 * 修改会议密码
	 * @param meetingId
	 * @param host_id
	 * @return
	 */
	public boolean modifyMeetingPwd(String apiKey,String apiToken,String meetingId,String host_id,String pwd);
	/**
	 * 修改会议密码通过MA方式
	 * @param accountId
	 * @param meetingId
	 * @param host_id
	 * @return
	 */
	public boolean modifyMeetingPwdMA(String apiKey,String apiToken,String accountId,String meetingId,String host_id,String pwd);
	
	
	/**
	 * 分页查询某个用户主持的所有会议
	 * @param host_id
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Map<String, Object> getMeetingsByHost(String apiKey,String apiToken,String hostId,int pageSize,int pageNo);
	/**
	 * 分页查询某个用户主持的所有会议通过MA方式
	 * @param accountId
	 * @param host_id
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Map<String, Object> getMeetingsByHostMA(String apiKey,String apiToken,String accountId,String hostId,int pageSize,int pageNo);
	
	
	
	/**
	 * 查询某个会议
	 * @param meetingId 会议ID
	 * @param host_id 主持人ID信息
	 * @return 会议状态信息的MAP
	 */
	public Map<String, Object> getMeeting(String apiKey,String apiToken,String meetingId, String hostId);
	/**
	 * 查询某个会议通过MA方式
	 * @param accountId
	 * @param meetingId 会议ID
	 * @param host_id 主持人ID信息
	 * @return 会议状态信息的MAP
	 */
	public Map<String, Object> getMeetingMA(String apiKey,String apiToken,String accountId,String meetingId, String hostId);
	
	
	
	
	/**
	 * 查询会议的状态
	 * @param meetingId
	 * @param hostId
	 * @return
	 */
	public int getMeetingStatus(String apiKey,String apiToken,String meetingId, String hostId);
	/**
	 * 查询会议的状态通过MA方式
	 * @param accountId
	 * @param meetingId
	 * @param hostId
	 * @return
	 */
	public int getMeetingStatusMA(String apiKey,String apiToken,String accountId,String meetingId, String hostId);
	
	
	
	/**
	 * 结束某个会议
	 * @param meetingId
	 * @param host_id
	 * @return
	 */
	public boolean endMeeting(String apiKey,String apiToken,String meetingId, String hostId);
	/**
	 * 结束某个会议通过MA方式
	 * @param accountId
	 * @param meetingId
	 * @param host_id
	 * @return
	 */
	public boolean endMeetingMA(String apiKey,String apiToken,String accountId,String meetingId, String hostId);
	
	
	
	/**
	 * 查询某个用户的所有会议
	 * @param hostId
	 * @return
	 */
	public List<ConfBase> getHostMeetinglist(String apiKey,String apiToken,String hostId);
	/**
	 * 查询某个用户的所有会议通过MA方式
	 * @param accountId
	 * @param hostId
	 * @return
	 */
	public List<ConfBase> getHostMeetinglistMA(String apiKey,String apiToken,String accountId,String hostId);
	
	
	/**
	 * H323/SIPZ终端接入
	 * @param id
	 * @param hostId
	 * @param pairCode
	 * @return
	 */
	public int pairH323OrSipTerminal(String apiKey,String apiToken,String id,String hostId,String pairCode);
	
	//public int getConfActiveStatus();
	
}
