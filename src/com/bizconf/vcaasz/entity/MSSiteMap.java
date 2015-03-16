package com.bizconf.vcaasz.entity;

/**
 * @desc MS服务器(组)和站点间的关系 
 * @author martin
 * @date 2013-8-8
 */
public class MSSiteMap {
	
	private int id;
	private int siteId;// 站点ID
	private int msGroupId; //ms服务器群组ID
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getMsGroupId() {
		return msGroupId;
	}
	public void setMsGroupId(int msGroupId) {
		this.msGroupId = msGroupId;
	}
	
	
	
}
