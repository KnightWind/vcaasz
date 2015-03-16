package com.bizconf.vcaasz.entity;

import java.io.Serializable;

public class ConfUserCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3358063708175312341L;
	
	private Integer confId;
	private Integer cycleId;
	private Long userCount;
	
	public ConfUserCount(){
		
	}
	public ConfUserCount(Integer confId,Integer cycleId,Long userCount){
		this.confId=confId;
		this.cycleId=cycleId;
		this.userCount=userCount;
	}
	public Integer getConfId() {
		return confId;
	}
	public void setConfId(Integer confId) {
		this.confId = confId;
	}
	public Integer getCycleId() {
		return cycleId;
	}
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}
	public Long getUserCount() {
		return userCount;
	}
	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}
	
	@Override
	public String toString() {
		return "ConfUserCount [confId=" + confId + ", cycleId=" + cycleId
				+ ", userCount=" + userCount + "]";
	}
	
	
	
}
