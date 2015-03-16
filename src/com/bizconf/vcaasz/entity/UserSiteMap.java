package com.bizconf.vcaasz.entity;
/**
 * @desc  这个类表示host activeuser 主持人和对应虚拟子站点的关系
 * @author martin
 * @date 2013-6-19
 */
public class UserSiteMap {
	
	private Integer id;
	//主持人ID
	private Integer userId;
	//对应站点ID
	private Integer siteId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
}
