package com.bizconf.vcaasz.entity;

/** 
 *   
 * @package com.bizconf.vcaasz.entity 
 * @description: TODO
 * @author Martin
 * @date 2014年7月8日 下午3:50:20 
 * @version 
 */
public class MonthlyReport {
	
	
	private int meetingCount;
	
	
	private int totalDuration;
	
	
	private int totalParticipants;


	public int getMeetingCount() {
		return meetingCount;
	}


	public void setMeetingCount(int meetingCount) {
		this.meetingCount = meetingCount;
	}


	public int getTotalDuration() {
		return totalDuration;
	}


	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}


	public int getTotalParticipants() {
		return totalParticipants;
	}


	public void setTotalParticipants(int totalParticipants) {
		this.totalParticipants = totalParticipants;
	}
	
	
	/**
	 * 用于前台页面显示的总会议时长
	 * @return
	 */
	public int getShowDuration(){
		int h = this.totalDuration / 60;

		return h;
	}
}
