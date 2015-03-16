package com.bizconf.vcaasz.entity;

import java.io.Serializable;
import java.util.Date;

public class Empower implements Serializable {

	private static final long serialVersionUID = 468501872493399782L;
	private Integer id;
	private Integer siteId;
	private Integer userId;
	private Integer funCode;
	private Integer emFlag;
	private Integer emOpen;
	private Date emTime;
	private Integer emUser;
	private Integer emUserType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFunCode() {
		return funCode;
	}
	public void setFunCode(Integer funCode) {
		this.funCode = funCode;
	}
	public Integer getEmFlag() {
		return emFlag;
	}

	public void setEmFlag(Integer emFlag) {
		this.emFlag = emFlag;
	}
	 
	public Integer getEmOpen() {
		return emOpen;
	}
	public void setEmOpen(Integer emOpen) {
		this.emOpen = emOpen;
	}
	public Date getEmTime() {
		return emTime;
	}
	public void setEmTime(Date emTime) {
		this.emTime = emTime;
	}
	public Integer getEmUser() {
		return emUser;
	}
	public void setEmUser(Integer emUser) {
		this.emUser = emUser;
	}
	public Integer getEmUserType() {
		return emUserType;
	}
	public void setEmUserType(Integer emUserType) {
		this.emUserType = emUserType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empower other = (Empower) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Empower [id=" + id + ", siteId=" + siteId + ", userId="
				+ userId + ", funCode=" + funCode + ", emFlag=" + emFlag
				+ ", emOpen=" + emOpen + ", emTime=" + emTime + ", emUser="
				+ emUser + ", emUserType=" + emUserType + "]";
	}

}
