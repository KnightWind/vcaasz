package com.bizconf.vcaasz.entity;

public class CountLicenseObject implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3484677746874478051L;
	
	private String licDate;
	private Integer licCount;
	public String getLicDate() {
		return licDate;
	}
	public void setLicDate(String licDate) {
		this.licDate = licDate;
	}
	public Integer getLicCount() {
		return licCount;
	}
	public void setLicCount(Integer licCount) {
		this.licCount = licCount;
	}
	@Override
	public String toString() {
		return "CountLicenseObject [licDate=" + licDate + ", licCount="
				+ licCount + "]";
	}
	
	



}
