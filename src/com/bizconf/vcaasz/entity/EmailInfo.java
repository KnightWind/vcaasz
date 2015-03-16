package com.bizconf.vcaasz.entity;

import java.util.Date;

import com.bizconf.vcaasz.constant.EmailConstant;
import com.bizconf.vcaasz.util.DateUtil;



public class EmailInfo implements java.io.Serializable {

	
	private static final long serialVersionUID = -6708599487587560869L;
	private Long id;
	private Integer siteId; //站点ID
	private String serverHost; //服务器名称 
	private String serverPort; //默认25
	private String fromEmail; //
	private String fromName; // 
	private String userName; //邮箱验证名称
	private String userPass; //邮箱验证密码
	private Boolean validate; //默认true
	private String emailAccepter; //接受者
	private String emailSubject;//主题
	private String contentType; //html  txt
	private String emailContent;//
	private Date createTime; //系统当前时间
	private Integer sendCount; //默认0
	private Integer sendFlag; //发送成功设置   增加 默认1
	private Integer retryCount;//最大重发次数 默认10
	private Date sendTime; //默认1970 01 01 邮件发送之后
	private String[] ccEmails;
	private String[] bccEmails;
	
	private Integer warnFlag = 1;
	private Integer warnType = EmailConstant.WARNTYPE_REQUEST;
	private String confAddress; //
	private String warnSubject;
	private String timeZone;
	private Date stratTime;
	private Date endTime;
	private Integer gapMinute;
	private Integer beforeMinute;
	private Integer warnCount;
	
	//周期ID用于创建周期提醒
	private int cycleId;
	//邮件唯一标识 用于邮件提醒的修改
	private String uid;
	
	//约会的组织者
	private String organizerEmail;
	//约会组织者名称
	private String organizerName;
	
	
	// Constructors

	/** default constructor */
	public EmailInfo() {
		this.siteId = 0;
		this.serverHost = "";
		this.serverPort = "";
		this.fromEmail = "";
		this.fromName = "";
		this.userName = "";
		this.userPass = "";
		this.validate = false;
		this.emailAccepter = "";
		this.emailSubject = "";
		this.contentType = "";
		this.emailContent = "";
		this.createTime = DateUtil.getGmtDate(null);
		this.sendCount = 0;
		this.sendFlag = 1;
		this.retryCount = 0;
		this.sendTime =  DateUtil.getGmtDate(null);
		this.warnFlag = 0;
		this.warnType = 0;
		this.confAddress = "";
		this.warnSubject = "";
		this.stratTime =  DateUtil.getGmtDate(null);
		this.endTime =  DateUtil.getGmtDate(null);
		this.gapMinute = 0;
		this.timeZone="";
		this.beforeMinute = 0;
		this.warnCount = 0;
		
	}

	/** full constructor */
	public EmailInfo(Integer siteId, String serverHost, String serverPort,
			String fromEmail, String fromName, String userName,
			String userPass, Boolean validate, String emailAccepter,
			String emailSubject, String contentType, String emailContent,
			Date createTime, Integer sendCount, Integer sendFlag,
			Integer retryCount, Date sendTime, Integer warnFlag,
			Integer warnType, String confAddress, String warnSubject,String timeZone,
			Date stratTime, Date endTime, Integer gapMinute,
			Integer beforeMinute, Integer warnCount) {
		this.siteId = siteId;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.fromEmail = fromEmail;
		this.fromName = fromName;
		this.userName = userName;
		this.userPass = userPass;
		this.validate = validate;
		this.emailAccepter = emailAccepter;
		this.emailSubject = emailSubject;
		this.contentType = contentType;
		this.emailContent = emailContent;
		this.createTime = createTime;
		this.sendCount = sendCount;
		this.sendFlag = sendFlag;
		this.retryCount = retryCount;
		this.sendTime = sendTime;
		this.warnFlag = warnFlag;
		this.warnType = warnType;
		this.confAddress = confAddress;
		this.warnSubject = warnSubject;
		this.stratTime = stratTime;
		this.endTime = endTime;
		this.gapMinute = gapMinute;
		this.timeZone=timeZone;
		this.beforeMinute = beforeMinute;
		this.warnCount = warnCount;
	}

	// Property accessors
	
	public Long getId() {
		return this.id;
	}

	public int getCycleId() {
		return cycleId;
	}

	public void setCycleId(int cycleId) {
		this.cycleId = cycleId;
	}

	public String[] getCcEmails() {
		return ccEmails;
	}

	public void setCcEmails(String[] ccEmails) {
		this.ccEmails = ccEmails;
	}

	public String[] getBccEmails() {
		return bccEmails;
	}

	public void setBccEmails(String[] bccEmails) {
		this.bccEmails = bccEmails;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getServerHost() {
		return this.serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getFromEmail() {
		return this.fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromName() {
		return this.fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public Boolean getValidate() {
		return this.validate;
	}

	public void setValidate(Boolean validate) {
		this.validate = validate;
	}

	public String getEmailAccepter() {
		return this.emailAccepter;
	}

	public void setEmailAccepter(String emailAccepter) {
		this.emailAccepter = emailAccepter;
	}

	public String getEmailSubject() {
		return this.emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getEmailContent() {
		return this.emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSendCount() {
		return this.sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public Integer getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
	}

	public Integer getRetryCount() {
		return this.retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getWarnFlag() {
		return this.warnFlag;
	}

	public void setWarnFlag(Integer warnFlag) {
		this.warnFlag = warnFlag;
	}

	public Integer getWarnType() {
		return this.warnType;
	}

	public void setWarnType(Integer warnType) {
		this.warnType = warnType;
	}

	public String getConfAddress() {
		return this.confAddress;
	}

	public void setConfAddress(String confAddress) {
		this.confAddress = confAddress;
	}

	public String getWarnSubject() {
		return this.warnSubject;
	}

	public void setWarnSubject(String warnSubject) {
		this.warnSubject = warnSubject;
	}

	
	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Date getStratTime() {
		return this.stratTime;
	}

	public void setStratTime(Date stratTime) {
		this.stratTime = stratTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getGapMinute() {
		return this.gapMinute;
	}

	public void setGapMinute(Integer gapMinute) {
		this.gapMinute = gapMinute;
	}

	public Integer getBeforeMinute() {
		return this.beforeMinute;
	}

	public void setBeforeMinute(Integer beforeMinute) {
		this.beforeMinute = beforeMinute;
	}

	public Integer getWarnCount() {
		return this.warnCount;
	}

	public void setWarnCount(Integer warnCount) {
		this.warnCount = warnCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	

	public String getOrganizerEmail() {
		return organizerEmail;
	}

	public void setOrganizerEmail(String organizerEmail) {
		this.organizerEmail = organizerEmail;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailInfo other = (EmailInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailInfo [id=" + id + ", siteId=" + siteId + ", serverHost="
				+ serverHost + ", serverPort=" + serverPort + ", fromEmail="
				+ fromEmail + ", fromName=" + fromName + ", userName="
				+ userName + ", userPass=" + userPass + ", validate="
				+ validate + ", emailAccepter=" + emailAccepter
				+ ", emailSubject=" + emailSubject + ", contentType="
				+ contentType + ", emailContent=" + emailContent
				+ ", createTime=" + createTime + ", sendCount=" + sendCount
				+ ", sendFlag=" + sendFlag + ", retryCount=" + retryCount
				+ ", sendTime=" + sendTime + ", warnFlag=" + warnFlag
				+ ", warnType=" + warnType + ", confAddress=" + confAddress
				+ ", warnSubject=" + warnSubject + ", timeZone=" + timeZone
				+ ", stratTime=" + stratTime + ", endTime=" + endTime
				+ ", gapMinute=" + gapMinute + ", beforeMinute=" + beforeMinute
				+ ", warnCount=" + warnCount + "]";
	}

}