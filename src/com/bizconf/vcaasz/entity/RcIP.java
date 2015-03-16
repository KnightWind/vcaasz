package com.bizconf.vcaasz.entity;

import java.io.Serializable;

/**
 * @desc 数据中心的RC对照表
 * @author Darren
 * @date 2015-2-6
 */
public class RcIP implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	//数据中心id
	private Integer centerId;
	
	//ip地址
	private String ipAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
