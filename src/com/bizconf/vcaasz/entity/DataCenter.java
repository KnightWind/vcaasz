package com.bizconf.vcaasz.entity;

import java.io.Serializable;

/**
 * @desc 数据中心实体
 * @author Darren
 * @date 2014-12-23
 */
@SuppressWarnings("serial")
public class DataCenter implements Serializable {

	private Integer id;
	
	/** 数据中心名字 */
	private String name;
	
	/** zoom数据中心key  */
	private String apiKey;
	
	/** zoom数据中心token  */
	private String apiToken;
	
	/** 是否可以被操作删除或者修改 :0-是不可以操作，1-可以操作*/
	private int isOperation;
	
	/** subAccountId */
	private String accountId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public int getIsOperation() {
		return isOperation;
	}

	public void setIsOperation(int isOperation) {
		this.isOperation = isOperation;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "DataCenter [id=" + id + ", name=" + name + ", apiKey=" + apiKey
				+ ", apiToken=" + apiToken + ", isOperation=" + isOperation
				+ ", accountId=" + accountId + "]";
	}

}
