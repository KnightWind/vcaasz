package com.bizconf.vcaasz.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 发送短信实体
 * @author Darren
 * @date 2014-9-28
 */
public class SMSInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	/** 会议Id */
	private Integer confBaseId;
	/** 主持人Id */
	private Integer userBaseId;
	/** 发送给给参会者的手机号 （可以为多个以逗号分隔） */
	private String sendNum;
	/** 发送的短信内容 */
	private String smsContent;
	/** 发送时间  */
	private Date sendTime;
	/** 发送成功标志 */
	private Integer sendFlag;
	/** 最大重发次数 默认10 */
	private Integer retryCount;//
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getConfBaseId() {
		return confBaseId;
	}
	public void setConfBaseId(Integer confBaseId) {
		this.confBaseId = confBaseId;
	}
	public Integer getUserBaseId() {
		return userBaseId;
	}
	public void setUserBaseId(Integer userBaseId) {
		this.userBaseId = userBaseId;
	}
	public String getSendNum() {
		return sendNum;
	}
	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
	}
	public Integer getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
