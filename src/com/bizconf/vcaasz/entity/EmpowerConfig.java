package com.bizconf.vcaasz.entity;

import java.io.Serializable;
import java.util.Date;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.util.DateUtil;

public class EmpowerConfig implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4624021348714631509L;
	
	/*
	 *站点ID号
	 */
	private Integer siteId;
	/*
	 * 用户ID号，当用户ID号为0时，则为站点的授权内容
	 */
	private Integer userId;
	/*
	 * 电话功能：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer phoneFlag = SiteConstant.EMPOWER_DISABLED;
	/*
	 * 外呼：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer outCallFlag = SiteConstant.EMPOWER_DISABLED;
	/*
	 * 自动外呼功能：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer autoFlag = SiteConstant.EMPOWER_DISABLED;
	/*
	 * 媒体共享功能：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer shareMediaFlag = SiteConstant.EMPOWER_DISABLED;
	/*
	 * 录制功能：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer recordFlag = SiteConstant.EMPOWER_DISABLED;
	
	/*
	 * 文档共享 ：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer shareDocsFlag=SiteConstant.EMPOWER_DISABLED;
	
	/*
	 * 屏幕共享 ：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer  shareScreenFlag=SiteConstant.EMPOWER_DISABLED;

	/*
	 * 白板 ：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer  whiteBoardFlag=SiteConstant.EMPOWER_DISABLED;
	/*
	 * 笔记 ：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer  noteFlag=SiteConstant.EMPOWER_DISABLED;
	/*
	 * 视频 ：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer  videoFlag=SiteConstant.EMPOWER_ENABLED;
	/*
	 * 音频：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer  audioFlag=SiteConstant.EMPOWER_ENABLED;
	/*
	 * 公告 ：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer  noticeFlag=SiteConstant.EMPOWER_DISABLED;
	/*
	 * 文件传输：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer  fileFlag=SiteConstant.EMPOWER_DISABLED;
	/*
	 * 问卷调查：对应SiteConstant.EMPOWER_DISABLED与SiteConstant.EMPOWER_ENABLED的值
	 */
	private Integer  questionFlag=SiteConstant.EMPOWER_DISABLED;
	
	/*
	 *视频路数 
	 */
	private Integer videoNumber = 16 ;
	
	/*
	 * 音频路数
	 */
	private Integer audioNumber = 16;

	/*
	 * 授权的最大分辨率（或者画面质量）
	 */
	private Integer dpiFlag = SiteConstant.EMPOWER_ENABLED;
	/*
	 * 授权的最大分辨率（或者画面质量）
	 */
	private Integer dpiNumber = ConfConstant.CONF_VIDEO_TYPE_FLUENCY_CODE;
	
	/*
	 * 操作时间功能：对应GMT时间
	 */
	private Date emTime;
	/*
	 * 操作操作者ID号：如果是系统管理员则是SystemUser的ID号，如果是企业管理员则是Userbase的ID号
	 */
	private Integer emUser;
	/*
	 * 操作操作者ID号类型：对应ConstantUtil中的
	 * 		USERTYPE_ADMIN、USERTYPE_ADMIN_SUPPER、USERTYPE_SYSTEM、USERTYPE_SYSTEM_SUPPER
	 */
	private Integer emUserType;
	
	
	public EmpowerConfig(){
		
	}
	
	
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPhoneFlag() {
		return phoneFlag;
	}
	public void setPhoneFlag(Integer phoneFlag) {
		this.phoneFlag = phoneFlag;
	}
	public Integer getAutoFlag() {
		return autoFlag;
	}
	public void setAutoFlag(Integer autoFlag) {
		this.autoFlag = autoFlag;
	}
	public Integer getShareMediaFlag() {
		return shareMediaFlag;
	}
	public void setShareMediaFlag(Integer shareMediaFlag) {
		this.shareMediaFlag = shareMediaFlag;
	}
	public Integer getRecordFlag() {
		return recordFlag;
	}
	public void setRecordFlag(Integer recordFlag) {
		this.recordFlag = recordFlag;
	}
	
	
	
	public Integer getShareDocsFlag() {
		return shareDocsFlag;
	}


	public void setShareDocsFlag(Integer shareDocsFlag) {
		this.shareDocsFlag = shareDocsFlag;
	}


	public Integer getShareScreenFlag() {
		return shareScreenFlag;
	}


	public void setShareScreenFlag(Integer shareScreenFlag) {
		this.shareScreenFlag = shareScreenFlag;
	}


	public Integer getWhiteBoardFlag() {
		return whiteBoardFlag;
	}


	public void setWhiteBoardFlag(Integer whiteBoardFlag) {
		this.whiteBoardFlag = whiteBoardFlag;
	}


	public Integer getNoteFlag() {
		return noteFlag;
	}


	public void setNoteFlag(Integer noteFlag) {
		this.noteFlag = noteFlag;
	}


	public Integer getVideoFlag() {
		return videoFlag;
	}


	public void setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
	}


	public Integer getAudioFlag() {
		return audioFlag;
	}


	public void setAudioFlag(Integer audioFlag) {
		this.audioFlag = audioFlag;
	}


	public Integer getNoticeFlag() {
		return noticeFlag;
	}


	public void setNoticeFlag(Integer noticeFlag) {
		this.noticeFlag = noticeFlag;
	}


	public Integer getFileFlag() {
		return fileFlag;
	}


	public void setFileFlag(Integer fileFlag) {
		this.fileFlag = fileFlag;
	}


	public Integer getQuestionFlag() {
		return questionFlag;
	}


	public void setQuestionFlag(Integer questionFlag) {
		this.questionFlag = questionFlag;
	}


	public Integer getVideoNumber() {
		return videoNumber;
	}


	public void setVideoNumber(Integer videoNumber) {
		this.videoNumber = videoNumber;
	}


	public Integer getAudioNumber() {
		return audioNumber;
	}


	public void setAudioNumber(Integer audioNumber) {
		this.audioNumber = audioNumber;
	}


	public Integer getDpiFlag() {
		return dpiFlag;
	}


	public void setDpiFlag(Integer dpiFlag) {
		this.dpiFlag = dpiFlag;
	}


	public Integer getDpiNumber() {
		return dpiNumber;
	}


	public void setDpiNumber(Integer dpiNumber) {
		this.dpiNumber = dpiNumber;
	}


	public Date getEmTime() {
		return emTime;
	}
	public void setEmTime(Date emTime) {
		this.emTime = emTime;
	}

	public Integer getEmUser() {
		return emUser;
	}
	public void setEmUser(Integer emUser) {
		this.emUser = emUser;
	}
	public Integer getEmUserType() {
		return emUserType;
	}
	public void setEmUserType(Integer emUserType) {
		this.emUserType = emUserType;
	}
	
	
	
	public Integer getOutCallFlag() {
		return outCallFlag;
	}


	public void setOutCallFlag(Integer outCallFlag) {
		this.outCallFlag = outCallFlag;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		EmpowerConfig other = (EmpowerConfig) obj;
		if (siteId == null) {
			if (other.siteId != null)
				return false;
		} else if (!siteId.equals(other.siteId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmpowerConfig [siteId=" + siteId + ", userId=" + userId
				+ ", phoneFlag=" + phoneFlag + ", outCallFlag=" + outCallFlag
				+ ", autoFlag=" + autoFlag + ", shareMediaFlag="
				+ shareMediaFlag + ", recordFlag=" + recordFlag
				+ ", shareDocsFlag=" + shareDocsFlag + ", shareScreenFlag="
				+ shareScreenFlag + ", whiteBoardFlag=" + whiteBoardFlag
				+ ", noteFlag=" + noteFlag + ", videoFlag=" + videoFlag
				+ ", audioFlag=" + audioFlag + ", noticeFlag=" + noticeFlag
				+ ", fileFlag=" + fileFlag + ", questionFlag=" + questionFlag
				+ ", videoNumber=" + videoNumber + ", audioNumber="
				+ audioNumber + ", dpiFlag=" + dpiFlag + ", dpiNumber="
				+ dpiNumber + ", emTime=" + emTime + ", emUser=" + emUser
				+ ", emUserType=" + emUserType + "]";
	}


	/**
	 * 创建(修改)站点时初始化对象
	 * shhc
	 * 2013-3-27
	 */
	public void initEmpower(SystemUser systemUser){
		this.setEmTime(DateUtil.getGmtDate(null));
		this.setEmUser(systemUser.getId());
		this.setEmUserType(systemUser.getSysType());
		this.setUserId(0);
	}
	
	/**
	 * 创建会议权限全局设置时初始化对象
	 * shhc
	 * 2013-3-27
	 */
	public void initEmpower(UserBase currentSiteAdmin){
		this.setSiteId(currentSiteAdmin.getSiteId());
		this.setEmTime(DateUtil.getGmtDate(null));
		this.setEmUser(currentSiteAdmin.getId());
		this.setEmUserType(currentSiteAdmin.getUserType());
		this.setUserId(-1);
	}
	
	//克隆对象
	public Object clone(){
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

}
