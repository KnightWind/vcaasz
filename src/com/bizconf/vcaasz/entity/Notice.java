package com.bizconf.vcaasz.entity;

import java.util.Date;

/**
 * 公告信息
 * @author shhc
 *
 */
public class Notice implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -3490221480376848263L;
	
	/**
	 * 主键ID号
	 */
	private Integer id;
	
	/**
	 * 站点ID号
	 */
	private Integer siteId;
	
	/**
	 * 公告标题
	 */
	private String title;
	
	/**
	 * 公告内容
	 */
	private String content;
	
	/**
	 * 发布状态
	 *  0、无效状态
	 *	1、发布中
	 *	2、停止发布
	 */
	private Integer noticeStatus;
	
	/**
	 * 发布开始日期
	 */
	private Date startTime;
	
	
	/**
	 * 发布结束日期
	 */
	private Date stopTime;
	
	/**
	 * 查看次数
	 */
	private Integer browseCount;
	
	/**
	 * 创建日期
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
	
	/**
	 * 是否登录用户可见
	 * 0:所有可见 1:登录用户可见
	 */
	private int loginedFlag;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** full constructor */
	public Notice(Integer id, Integer siteId, String title, String content,
			Integer noticeStatus, Date startTime, Date stopTime,
			Integer browseCount, Date createTime, Integer createUser,
			Integer delFlag, Date delTime, Integer delUser, int loginedFlag) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.title = title;
		this.content = content;
		this.noticeStatus = noticeStatus;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.browseCount = browseCount;
		this.createTime = createTime;
		this.createUser = createUser;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
		this.loginedFlag = loginedFlag;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	 
	 

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
 

	public Integer getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}


	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public Integer getBrowseCount() {
		return this.browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
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
	
	public int getLoginedFlag() {
		return loginedFlag;
	}

	public void setLoginedFlag(int loginedFlag) {
		this.loginedFlag = loginedFlag;
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
		Notice other = (Notice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", siteId=" + siteId + ", title=" + title
				+ ", content=" + content + ", noticeStatus=" + noticeStatus
				+ ", startTime=" + startTime + ", stopTime=" + stopTime
				+ ", browseCount=" + browseCount + ", createTime=" + createTime
				+ ", createUser=" + createUser + ", delFlag=" + delFlag
				+ ", delTime=" + delTime + ", delUser=" + delUser
				+ ", loginedFlag=" + loginedFlag + "]";
	}

}