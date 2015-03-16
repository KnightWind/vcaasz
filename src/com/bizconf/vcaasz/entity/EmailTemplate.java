package com.bizconf.vcaasz.entity;

import java.util.Date;

/**
 * 邮件模板
 * @author shhc
 *
 */
public class EmailTemplate implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -7836344417062193715L;
	
	/**
	 * 模板主键ID
	 */
	private Integer id;
	
	/**
	 * 站点ID号
	 */
	private Integer siteId;
	
	/**
	 * 模板类型
	 * 1.创建会议者模板
	 * 2.被邀请者模板
	 * 3.修改会议模板
	 * 4.取消会议模板
	 */
	private Integer emailType;
	
	/**
	 * 1：是
	 * 0: 否
	 * 是否属于系统恢复模板保留模板
	 */
	private Integer sysReserveFlag;
	
	/**
	 * 邮件标题
	 */
	private String emailTitle;
	
	/**
	 * 邮件内容
	 * 邮件模板最后一定要放一下BIZ支持信息
	 */
	private String emailContent;
	
	/**
	 * 模板版本号
	 */
	private Integer tempVersion;
	
	/**
	 * 语言
	 */
	private String language;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 创建者ID号
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
	 * 删除者ID号
	 */
	private Integer delUser;

	// Constructors

	/** default constructor */
	public EmailTemplate() {
	}

	/** full constructor */
	public EmailTemplate(Integer siteId, Integer emailType, String emailTitle,
			String emailContent,String language, Date createTime, Integer createUser,
			Integer delFlag, Date delTime, Integer delUser,Integer sysReserveFlag,
			Integer tempVersion) {
		this.siteId = siteId;
		this.emailType = emailType;
		this.emailTitle = emailTitle;
		this.emailContent = emailContent;
		this.language = language;
		this.createTime = createTime;
		this.createUser = createUser;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
		this.sysReserveFlag = sysReserveFlag;
		this.tempVersion = tempVersion;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getEmailType() {
		return this.emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public String getEmailTitle() {
		return this.emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailContent() {
		return this.emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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
	
	
	public Integer getSysReserveFlag() {
		return sysReserveFlag;
	}

	public void setSysReserveFlag(Integer sysReserveFlag) {
		this.sysReserveFlag = sysReserveFlag;
	}
	

	public Integer getTempVersion() {
		return tempVersion;
	}

	public void setTempVersion(Integer tempVersion) {
		this.tempVersion = tempVersion;
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
		EmailTemplate other = (EmailTemplate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailTemplate [id=" + id + ", siteId=" + siteId
				+ ", emailType=" + emailType + ", emailTitle=" + emailTitle
				+ ", emailContent=" + emailContent + ", createTime="
				+ createTime + ", createUser=" + createUser + ", delFlag="
				+ delFlag + ", delTime=" + delTime + ", delUser=" + delUser
				+ "]";
	}

}