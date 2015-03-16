package com.bizconf.vcaasz.entity;

/**
 * @desc MS群组和MS的对应关系
 * @author martin
 * @date 2013-8-8
 */
public class MSGroupMap {
	
	private int id;
	private int groupId; //群组ID
	private int msId;//ms服务器ID
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getMsId() {
		return msId;
	}
	public void setMsId(int msId) {
		this.msId = msId;
	}
	
	
}
