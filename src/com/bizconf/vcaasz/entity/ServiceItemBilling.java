package com.bizconf.vcaasz.entity;

import java.io.Serializable;

/**
 * @desc 账单对应的服务项目
 * @author Darren
 * @date 2014-11-4
 */
@SuppressWarnings("serial")
public class ServiceItemBilling implements Serializable {

	/**
	 * 服务项id
	 * */
	private int itemId;
	/**
	 * NameHost的端口数
	 * */
	private int numPatis;
	
	/**
	 * 服务项数量
	 * */
	private int itemCount;
	/**
	 * 服务项金额
	 * */
	private float itemCharge;
	
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getNumPatis() {
		return numPatis;
	}
	public void setNumPatis(int numPatis) {
		this.numPatis = numPatis;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public float getItemCharge() {
		return itemCharge;
	}
	public void setItemCharge(float itemCharge) {
		this.itemCharge = itemCharge;
	}
	
}
