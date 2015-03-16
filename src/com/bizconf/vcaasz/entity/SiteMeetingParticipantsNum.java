package com.bizconf.vcaasz.entity;

/** 
 *   
 * @package com.bizconf.vcaasz.entity 
 * @description: 用来存储站点-会议场数 会议人次 或主持人 会议场数 会议人次 的统计数据
 * @author Martin
 * @date 2014年8月25日 下午4:57:00 
 * @version 
 */
public class SiteMeetingParticipantsNum {
	
	/**
	 * 站点ID
	 */
	int siteId;
	
	/**
	 * 站点下的会议场次
	 */
	long meetingNum;
	
	/**
	 * 站点下的参会者人数
	 */
	long participantsNum;
	
	/**
	 * 主持人ID
	 */
	int hostId;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public long getMeetingNum() {
		return meetingNum;
	}

	public void setMeetingNum(long meetingNum) {
		this.meetingNum = meetingNum;
	}

	public long getParticipantsNum() {
		return participantsNum;
	}

	public void setParticipantsNum(long participantsNum) {
		this.participantsNum = participantsNum;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	
	
}
