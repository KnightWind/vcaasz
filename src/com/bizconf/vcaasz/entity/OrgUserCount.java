package com.bizconf.vcaasz.entity;

import java.io.Serializable;

public class OrgUserCount implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -796857302129363252L;
	

	private String orgId;
	private String orgCode;
	private String userCount;
	 
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getUserCount() {
		return userCount;
	}
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	@Override
	public String toString() {
		return "OrgUserCount [orgId=" + orgId + ", orgCode=" + orgCode
				+ ", userCount=" + userCount + "]";
	}
	
	
	
	
}
