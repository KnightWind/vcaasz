package com.bizconf.vcaasz.entity;

import java.io.Serializable;

/**
 * 统计参会人数
 * @author wangyong
 *
 */
public class ConfUserLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -323329207021962851L;
	
	/*
	 * 会议ID
	 */
	private Integer confId;

	/*
	 * 参会人总人数
	 */
	private Integer totalUser;
	
	/*
	 * PC用户数
	 */
	private Integer pcUser;
	
	/*
	 * 电话用户数
	 */
	private Integer telUser;

	public ConfUserLog(Integer confId, Integer totalUser, Integer pcUser,
			Integer telUser) {
		super();
		this.confId = confId;
		this.totalUser = totalUser;
		this.pcUser = pcUser;
		this.telUser = telUser;
	}

	public ConfUserLog() {
		
	}

	public Integer getConfId() {
		return confId;
	}

	public void setConfId(Integer confId) {
		this.confId = confId;
	}

	public Integer getTotalUser() {
		return this.pcUser + this.telUser;
	}

	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}

	public Integer getPcUser() {
		return pcUser;
	}

	public void setPcUser(Integer pcUser) {
		this.pcUser = pcUser;
	}

	public Integer getTelUser() {
		return telUser;
	}

	public void setTelUser(Integer telUser) {
		this.telUser = telUser;
	}

	@Override
	public String toString() {
		return "ConfUserLog [confId=" + confId + ", totalUser=" + totalUser
				+ ", pcUser=" + pcUser + ", telUser=" + telUser + "]";
	}
	
}
