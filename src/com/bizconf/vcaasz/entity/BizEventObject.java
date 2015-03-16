package com.bizconf.vcaasz.entity;

import java.util.ArrayList;
import java.util.List;

public class BizEventObject implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3484677746874478061L;
	private Integer siteId;
	private Integer module;
	private String optDesc; 
	private Integer userType;
	private Integer userId;
	private Integer logStatus;
	private String userIp;
	private List<Object> objectList;
	
	public BizEventObject() {
		this.objectList=new ArrayList<Object>();
	}
	
	public BizEventObject(Integer siteId,Integer userType,Integer userId,Integer module, String optDesc, Integer logStatus,String userIp, List<Object> objectList) {
		this.siteId=siteId;
		this.userType=userType;
		this.userId=userId;
		this.module = module;
		this.optDesc = optDesc;
		this.logStatus=logStatus;
		this.userIp = userIp;
		this.objectList = objectList;
	}
	
	//向操作对象列表中增加一个对象
	public void addObject(Object object){
		this.objectList.add(object);
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getModule() {
		return module;
	}

	public void setModule(Integer module) {
		this.module = module;
	}

	public String getOptDesc() {
		return optDesc;
	}

	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public List<Object> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}
	
	

}
