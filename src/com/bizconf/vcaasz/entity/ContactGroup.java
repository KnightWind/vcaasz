package com.bizconf.vcaasz.entity;

import java.util.Date;

/**
 * 通讯录群组
 * @author shhc
 *
 */
public class ContactGroup implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8143677724226917141L;
	
	/**
	 * 群组ID号
	 */
	private Integer id;
	
	/**
	 * 用户ID号
	 */
	private Integer userId;
	
	/**
	 * 站点ID号
	 */
	private Integer siteId;
	
	/**
	 * 群组名称
	 */
	private String groupName;
	
	/**
	 * 群组描述
	 */
	private String groupDesc;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 创建者ID
	 */
	private Integer createUser;
	
	/**
	 * 删除标志
	 */
	private Integer delFlag;
	
	/**
	 * 删除时间
	 */
	private Date delTime;
	
	/**
	 * 删除者ID
	 */
	private Integer delUser;

	// Constructors

	/** default constructor */
	public ContactGroup() {
	}

	/** full constructor */
	public ContactGroup(Integer userId, Integer siteId, String groupName,
			String groupDesc, Date createTime, Integer createUser,
			Short delFlag, Date delTime, Integer delUser) {
		this.userId = userId;
		this.siteId = siteId;
		this.groupName = groupName;
		this.groupDesc = groupDesc;
		this.createTime = createTime;
		this.createUser = createUser;
		this.delTime = delTime;
		this.delUser = delUser;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return this.groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	
	public Date getDelTime() {
		return this.delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public Integer getDelUser() {
		return this.delUser;
	}

	public void setDelUser(Integer delUser) {
		this.delUser = delUser;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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
		ContactGroup other = (ContactGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactGroup [id=" + id + ", userId=" + userId + ", siteId="
				+ siteId + ", groupName=" + groupName + ", groupDesc="
				+ groupDesc + ", createTime=" + createTime + ", createUser="
				+ createUser + ", delFlag=" + delFlag + ", delTime=" + delTime
				+ ", delUser=" + delUser + "]";
	}

}