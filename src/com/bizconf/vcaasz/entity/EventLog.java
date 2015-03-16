package com.bizconf.vcaasz.entity;


import java.util.Date;

/**
 * 操作事件日志
 * @author shhc
 *
 */
public class EventLog implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 4661421429301798950L;
	
	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 站点ID号
	 */
	private Integer siteId;
	
	/**
	 * 操作方式
	 * 操作类型：
	 * 1、insert ，
	 * 2、update，
	 * 3、delete 
	 * 4、Join Meeting
	 */
	private Integer optType;
	
	/**
	 * 功能模块
	 */
	private Integer funcModule;
	
	/**
	 * 操作描述
	 */
	private String optDesc;
	
	/**
	 * 操作时间
	 */
	private Date createTime;
	
	/**
	 * 日志状态
	 * 0、无效操作
	 * 1、成功
	 * 2、失败
	 */
	private Integer logStatus;
	
	/**
	 * 操作者类别
	 */
	private Integer userType;
	
	/**
	 * 操作者的ID号
	 */
	private Integer createUser;
	
	/**
	 * 操作者IP地址
	 */
	private String createIp;
	

	/**
	 * UserBase用户ID号，主要是对应普通用户的ID号
	 */
	private Integer userId;
	

	/**
	 * 会议ID号，主要是对应ConfBase的ID号
	 */
	private Integer confId;
	
	
	//被操作对象的ID（站点ID、用户ID、会议ID）
	
	private Integer optId;
	
	//被操作对象的名称（站点名称、用户登录名、会议名称）
	private String optName;
	
	// Constructors

	/** default constructor */
	public EventLog() {
	}

	/** full constructor */
	public EventLog(Integer siteId,Integer optType, Integer funcModule, String optDesc, Integer logStatus,
			Date createTime,Integer userType,Integer createUser, String createIp,Integer userId,Integer confId) {
		this.optType = optType;
		this.siteId=siteId;
		this.funcModule = funcModule;
		this.optDesc = optDesc;
		this.logStatus=logStatus;
		this.createTime = createTime;
		this.userType=userType;
		this.createUser = createUser;
		this.createIp = createIp;
		this.userId=userId;
		this.confId=confId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOptType() {
		return optType;
	}

	public void setOptType(Integer optType) {
		this.optType = optType;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getFuncModule() {
		return funcModule;
	}

	public void setFuncModule(Integer funcModule) {
		this.funcModule = funcModule;
	}

	public String getOptDesc() {
		return optDesc;
	}

	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}

	public Integer getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getConfId() {
		return confId;
	}

	public void setConfId(Integer confId) {
		this.confId = confId;
	}

	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
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
		EventLog other = (EventLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventLog [id=" + id + ", siteId=" + siteId + ", optType="
				+ optType + ", funcModule=" + funcModule + ", optDesc="
				+ optDesc + ", createTime=" + createTime + ", logStatus="
				+ logStatus + ", userType=" + userType + ", createUser="
				+ createUser + ", createIp=" + createIp + ", userId=" + userId
				+ ", confId=" + confId + ", optId=" + optId + ", optName="
				+ optName + "]";
	}

 
	 
}