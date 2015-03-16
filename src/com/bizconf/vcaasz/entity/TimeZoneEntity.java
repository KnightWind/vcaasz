package com.bizconf.vcaasz.entity;

public class TimeZoneEntity {
	private Integer timeZoneId;
	private String timeZone;
	private Integer offset;
	
	public TimeZoneEntity(Integer timeZoneId,String timeZone,Integer offset){
		this.timeZoneId=timeZoneId;
		this.timeZone=timeZone;
		this.offset=offset;
	}

	public Integer getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(Integer timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
