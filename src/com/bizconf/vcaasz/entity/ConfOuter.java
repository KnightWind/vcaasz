package com.bizconf.vcaasz.entity;

import java.util.Date;

import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;


/**
 * 通过
 * @author zhaost
 *
 */
public class ConfOuter implements java.io.Serializable {


	private static final long serialVersionUID = 7330655234964889320L;
	private Integer id;
	private String siteSign;
	private String mtgTitle;
	private String mtgKey;
	private String hostPwd;
	private String mtgPwd;
	private Integer confId;
	private Date createTime;
	private String userId;
	private String userName;
	private Integer userType;
	private Integer confStatus;
	/*
	 * 会议开始时间
	 * */
	private Date startTime;
	/*
	 * 会议结束时间
	 * */
	private Date endTime;
	
	public ConfOuter(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteSign() {
		return siteSign;
	}
	public void setSiteSign(String siteSign) {
		this.siteSign = siteSign;
	}
	public String getMtgTitle() {
		return mtgTitle;
	}
	public void setMtgTitle(String mtgTitle) {
		this.mtgTitle = mtgTitle;
	}
	public String getMtgKey() {
		return mtgKey;
	}
	public void setMtgKey(String mtgKey) {
		this.mtgKey = mtgKey;
	}
	public String getHostPwd() {
		return hostPwd;
	}
	public void setHostPwd(String hostPwd) {
		this.hostPwd = hostPwd;
	}
	public String getMtgPwd() {
		return mtgPwd;
	}
	public void setMtgPwd(String mtgPwd) {
		this.mtgPwd = mtgPwd;
	}
	public Integer getConfId() {
		return confId;
	}
	public void setConfId(Integer confId) {
		this.confId = confId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getConfStatus() {
		return confStatus;
	}

	public void setConfStatus(Integer confStatus) {
		this.confStatus = confStatus;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "ConfOuter [id=" + id + ", siteSign=" + siteSign + ", mtgTitle="
				+ mtgTitle + ", mtgKey=" + mtgKey + ", hostPwd=" + hostPwd
				+ ", mtgPwd=" + mtgPwd + ", confId=" + confId + ", createTime="
				+ createTime + ", userId=" + userId + ", userName=" + userName
				+ ", userType=" + userType + ", confStatus=" + confStatus
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	/**
	 * 通过外部outer接口进会，会议不存在时创建会议，初始化confOuter对象
	 */
	public void initConf(JoinConfOuter joinConfOuter){
		this.siteSign = joinConfOuter.getSiteSign();
		this.mtgKey = joinConfOuter.getMtgKey();
		this.hostPwd = joinConfOuter.getHostPwd();
		this.mtgPwd = joinConfOuter.getMtgPwd();
		this.mtgTitle = joinConfOuter.getMtgTitle();
		this.createTime = DateUtil.getGmtDate(null);
		this.userName = joinConfOuter.getUserName();
		this.userId = joinConfOuter.getUserId();
		this.userType = joinConfOuter.getUserType();
	}

}
