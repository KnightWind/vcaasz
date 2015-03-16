package com.bizconf.vcaasz.entity;

import java.util.Date;

/**
 * 邮件服务器设置
 * @author shhc
 *
 */
public class EmailConfig implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 3775442074622077419L;
	
	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 站点ID号
	 */
	private Integer siteId;
	
	/**
	 * 发送者邮箱服务器
	 */
	private String emailHost;
	
	/**
	 * 发件人邮箱
	 */
	private String emailSender;
	
	/**
	 * 发件人姓名
	 */
	private String emailName;
	
	/**
	 * 发送者帐号
	 */
	private String emailAccount;
	
	/**
	 * 发送者密码
	 */
	private String emailPassword;
	
	/**
	 * 版本
	 */
	private Integer emailVersion;
	
	/**
	 * 创建者ID
	 */
	private Integer createUser;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 删除标志
	 */
	private Integer delFlag;
	
	/**
	 * 删除者ID
	 */
	private Integer delUser;
	
	/**
	 * 删除时间
	 */
	private Date delTime;

	// Constructors

	/** default constructor */
	public EmailConfig() {
	}

	/** full constructor */
	public EmailConfig(Integer siteId, String emailHost, String emailSender,
			String emailName, String emailAccount, String emailPassword,Integer emailVersion,
			Integer createUser, Date createTime, Integer delFlag,
			Integer delUser, Date delTime) {
		this.siteId = siteId;
		this.emailHost = emailHost;
		this.emailSender = emailSender;
		this.emailName = emailName;
		this.emailAccount = emailAccount;
		this.emailPassword = emailPassword;
		this.emailVersion = emailVersion;
		this.createUser = createUser;
		this.createTime = createTime;
		this.delFlag = delFlag;
		this.delUser = delUser;
		this.delTime = delTime;
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

	public String getEmailHost() {
		return this.emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getEmailSender() {
		return this.emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getEmailName() {
		return this.emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public String getEmailAccount() {
		return this.emailAccount;
	}

	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}

	public String getEmailPassword() {
		return this.emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public Integer getEmailVersion() {
		return emailVersion;
	}

	public void setEmailVersion(Integer emailVersion) {
		this.emailVersion = emailVersion;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getDelUser() {
		return this.delUser;
	}

	public void setDelUser(Integer delUser) {
		this.delUser = delUser;
	}

	public Date getDelTime() {
		return this.delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
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
		EmailConfig other = (EmailConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailConfig [id=" + id + ", siteId=" + siteId + ", emailHost="
				+ emailHost + ", emailSender=" + emailSender + ", emailName="
				+ emailName + ", emailAccount=" + emailAccount
				+ ", emailPassword=" + emailPassword + ", emailVersion="
				+ emailVersion + ", createUser=" + createUser + ", createTime="
				+ createTime + ", delFlag=" + delFlag + ", delUser=" + delUser
				+ ", delTime=" + delTime + "]";
	}

 
}