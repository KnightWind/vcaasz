package com.bizconf.vcaasz.entity;

import java.util.Date;

/**
 * @desc MS服务器
 * @author martin
 * @date 2013-8-12
 */
public class MsServer {
	
	private int id;
	private String msIp; //ms 服务器IP
	private String msSupplier; //ms服务器提供商
	private String msName; //ms服务器名称
	private String msDesc; //ms备注
	private int inUse = 1; //是否可用
	private Date createTime;
	/**
	 * 删除标志
	 */
	private Integer delFlag = 1;
	/**
	 * 删除时间
	 */
	private Date delTime;
	/**
	 * 删除者ID号
	 */
	private Integer delUser;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsIp() {
		return msIp;
	}
	public void setMsIp(String msIp) {
		this.msIp = msIp;
	}
	public String getMsSupplier() {
		return msSupplier;
	}
	public void setMsSupplier(String msSupplier) {
		this.msSupplier = msSupplier;
	}
	public String getMsName() {
		return msName;
	}
	public void setMsName(String msName) {
		this.msName = msName;
	}
	public String getMsDesc() {
		return msDesc;
	}
	public void setMsDesc(String msDesc) {
		this.msDesc = msDesc;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	public Integer getDelUser() {
		return delUser;
	}
	public void setDelUser(Integer delUser) {
		this.delUser = delUser;
	}
	public int getInUse() {
		return inUse;
	}
	public void setInUse(int inUse) {
		this.inUse = inUse;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
