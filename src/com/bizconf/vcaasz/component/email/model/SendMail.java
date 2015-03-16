package com.bizconf.vcaasz.component.email.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendMail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9074118535539505418L;

	//发送邮件的服务器的IP和端口
	
		private String serverHost;
		private String serverPort;
		
	    // 是否需要身份验证    
	    private boolean validate = false;    

		
		//发送者信息
		private String fromEmail;
		private String fromName;
		private String userName;
		private String userPass;
		
		//邮件标题与内容
		private String subject;
		private String content;
		/*
		 * 邮件内容格式 ：txt 、 html
		 * */
		private String contentType;
		
		//邮件附件
	    private List<String> filePathList;
	    
	    //接收者邮箱
	    private String toEmail;
	    
	    //CC邮箱列表
	    private List<String> ccEmailList;
	    
	    //是否添加OUTLOOK日历 
	    private boolean calFlag;
	    
	    //时区
	    private String timeZone;
	    //会议地点
	    private String address;
	    
	    //会议开始时间
	    private Date startTime;
	    
	    //会议结束时间
	    private Date stopTime;
	    
	    //Outlook提醒标题
	    private String warnSubject;
	    
	    //提醒间隔分钟数
	    private Integer gapMinute;
	    
	    //提前提醒分钟数
	    private Integer beforeMinute;
	    
	    //提醒次数
	    private Integer warnCount;
	    
	    //邮件的唯一ID 用于邮件提醒的修改和取消
	    private String uid; 
	    
	    //提醒的类型，主要用于标识是否为取消类型
	    private int warnType;
	    
		//约会的组织者
		private String organizerEmail;
		//约会组织者名称
		private String organizerName;


	    
	    public SendMail(){
	    	this.ccEmailList=new ArrayList<String>();
	    	this.filePathList=new ArrayList<String>();
	    }
	    
	    
	    public void addCcEmail(String ccEmail){
	    	this.ccEmailList.add(ccEmail);
	    }
	    
	    public void addFilePath(String filePath){
	    	this.filePathList.add(filePath);
	    }
	    
	    
		public String getServerHost() {
			return serverHost;
		}

		public void setServerHost(String serverHost) {
			this.serverHost = serverHost;
		}

		public String getServerPort() {
			return serverPort;
		}

		public void setServerPort(String serverPort) {
			this.serverPort = serverPort;
		}

		public boolean isValidate() {
			return validate;
		}

		public void setValidate(boolean validate) {
			this.validate = validate;
		}

		public String getFromEmail() {
			return fromEmail;
		}

		public void setFromEmail(String fromEmail) {
			this.fromEmail = fromEmail;
		}

		public String getFromName() {
			return fromName;
		}

		public void setFromName(String fromName) {
			this.fromName = fromName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPass() {
			return userPass;
		}

		public void setUserPass(String userPass) {
			this.userPass = userPass;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		public List<String> getFilePathList() {
			return filePathList;
		}

		public void setFilePathList(List<String> filePathList) {
			this.filePathList = filePathList;
		}

		public String getToEmail() {
			return toEmail;
		}

		public void setToEmail(String toEmail) {
			this.toEmail = toEmail;
		}

		public List<String> getCcEmailList() {
			return ccEmailList;
		}

		public void setCcEmailList(List<String> ccEmailList) {
			this.ccEmailList = ccEmailList;
		}

		public boolean isCalFlag() {
			return calFlag;
		}

		public void setCalFlag(boolean calFlag) {
			this.calFlag = calFlag;
		}

		public String getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getStopTime() {
			return stopTime;
		}

		public void setStopTime(Date stopTime) {
			this.stopTime = stopTime;
		}

		public String getWarnSubject() {
			return warnSubject;
		}

		public void setWarnSubject(String warnSubject) {
			this.warnSubject = warnSubject;
		}

		public Integer getGapMinute() {
			return gapMinute;
		}

		public void setGapMinute(Integer gapMinute) {
			this.gapMinute = gapMinute;
		}

		public Integer getBeforeMinute() {
			return beforeMinute;
		}

		public void setBeforeMinute(Integer beforeMinute) {
			this.beforeMinute = beforeMinute;
		}

		public Integer getWarnCount() {
			return warnCount;
		}

		public void setWarnCount(Integer warnCount) {
			this.warnCount = warnCount;
		}


		public String getUid() {
			return uid;
		}


		public void setUid(String uid) {
			this.uid = uid;
		}


		public int getWarnType() {
			return warnType;
		}


		public void setWarnType(int warnType) {
			this.warnType = warnType;
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
	    
	    
	    
	    
	    
	    
}
