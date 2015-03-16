package com.bizconf.vcaasz.entity;

import com.bizconf.vcaasz.constant.MeetingStartType;

/** 
 * 
 * @package com.bizconf.vcaasz.entity 
 * @description: TODO 用户的会议默认设置
 * @author Martin
 * @date 2014年6月17日 下午5:20:28 
 * @version 
 */
public class ZoomDefaultConfig {
	
	private long id;
	
	private int userId;
	
	@Deprecated
	private String timeZone = "GMT+8";
	
	//是否支持先于主持人入会 0 false 1:TRUE
	private int optionJbh = 0;
	
	
	//会议开始类型 ：video 或 screen_share
	private int optionStartType = MeetingStartType.VIDEO.getStatus();
	
	@Deprecated
	private int timeZoneId;
	
	public int getTimeZoneId() {
		return timeZoneId;
	}


	public void setTimeZoneId(int timeZoneId) {
		this.timeZoneId = timeZoneId;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getTimeZone() {
		return timeZone;
	}


	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}


	public int getOptionJbh() {
		return optionJbh;
	}


	public void setOptionJbh(int optionJbh) {
		this.optionJbh = optionJbh;
	}


	public int getOptionStartType() {
		return optionStartType;
	}


	public void setOptionStartType(int optionStartType) {
		this.optionStartType = optionStartType;
	}
	
	
	
}
