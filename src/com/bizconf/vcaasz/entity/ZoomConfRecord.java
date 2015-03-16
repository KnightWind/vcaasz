package com.bizconf.vcaasz.entity;

import java.util.Date;

/** 
 *  
 * @package com.bizconf.vcaasz.entity 
 * @description: 记录保存从zoom数据中心查询出来的数据记录
 * @author Martin
 * @date 2014年7月8日 下午2:16:03 
 * @version 
 */
public class ZoomConfRecord {
	
	private long id;
	/**会议的zoomId*/
	private String zoomId;
	/**会议主题*/
	private String topic;
	/**会议时长  分钟*/
	private int duration;
	/**会议的开始时间*/
	private Date startTime;
	/**会议的结束时间*/
	private Date endTime;
	
	/**主持人ID*/
	private int hostId;
	
	/**
	 * 所属企业ID
	 */
	private int siteId;
	
	/**关联的会议会畅会议ID*/
	private int confId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getZoomId() {
		return zoomId;
	}

	public void setZoomId(String zoomId) {
		this.zoomId = zoomId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public int getConfId() {
		return confId;
	}

	public void setConfId(int confId) {
		this.confId = confId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	

 
	
	
	
	
	
}
