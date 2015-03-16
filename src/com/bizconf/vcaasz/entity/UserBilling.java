package com.bizconf.vcaasz.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @desc 每个Namehost账号的账单
 * @author Darren
 * @date 2014-11-4
 */
@SuppressWarnings("serial")
public class UserBilling implements Serializable{

	/**
	 * Namehost的id
	 * */
	private Integer hostId;
	/**
	 * Namehost的账号
	 * */
	private String account;
	/**
	 * Namehost的用户名
	 * */
	private String userName;
	
	/**
	 * 主持人的服务项目
	 * */
	private List<ServiceItemBilling> serviceItemBillings;

	/**
	 * 总金额
	 * */
	private float sumCharge;
	
	
	public Integer getHostId() {
		return hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<ServiceItemBilling> getServiceItemBillings() {
		return serviceItemBillings;
	}

	public void setServiceItemBillings(List<ServiceItemBilling> serviceItemBillings) {
		this.serviceItemBillings = serviceItemBillings;
	}

	public float getSumCharge() {
		return sumCharge;
	}

	public void setSumCharge(float sumCharge) {
		this.sumCharge = sumCharge;
	}
	
}
