package com.bizconf.vcaasz.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 会议报告参会者详情
 * @author Administrator darren
 * @date 2014-6-25
 */
@SuppressWarnings("serial")
public class ConfReportInfo implements Serializable {

	/**会议报告详情id*/
	private Integer id;
	
	/**与会者名字*/
	private String participantName;
	
	/**与会者加入时间*/
	private Date pStartTime;
	
	/**与会者离开时间*/
	private Date pLeaveTime;
	
	/** 会议报告表id*/
    private long confLogId;
    
	public long getConfLogId() {
		return confLogId;
	}

	public void setConfLogId(long confLogId) {
		this.confLogId = confLogId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public Date getpStartTime() {
		return pStartTime;
	}

	public void setpStartTime(Date pStartTime) {
		this.pStartTime = pStartTime;
	}

	public Date getpLeaveTime() {
		return pLeaveTime;
	}

	public void setpLeaveTime(Date pLeaveTime) {
		this.pLeaveTime = pLeaveTime;
	}

	@Override
	public String toString() {
		return "ConfReportInfo [id=" + id + ", participantName="
				+ participantName + ", pStartTime=" + pStartTime
				+ ", pLeaveTime=" + pLeaveTime + "]";
	}

}
