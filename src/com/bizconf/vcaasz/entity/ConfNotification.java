package com.bizconf.vcaasz.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 	接收zoom传递过来参数存放
 * @author Administrator
 * @date 2014-6-26
 */
@SuppressWarnings("serial")
public class ConfNotification implements Serializable {

	private Integer id;
	
	private String zoomId;//zoom会议id
	
	private String uuid;//zoom会议uuid
	
	private String status;//zoom会议状态, STARTED/ENDED
	
	private String hostId;//主持人id

	private Date recordDate = new Date();//记录时间

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZoomId() {
		return zoomId;
	}

	public void setZoomId(String zoomId) {
		this.zoomId = zoomId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	
}
