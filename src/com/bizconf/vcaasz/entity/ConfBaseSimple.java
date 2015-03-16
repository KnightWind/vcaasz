package com.bizconf.vcaasz.entity;

import java.util.Date;

import com.bizconf.vcaasz.constant.ConfStatus;
import com.bizconf.vcaasz.util.DateUtil;

public class ConfBaseSimple implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -556421752454369136L;
	
	private Integer id;
 
	/*
	 * 站点ID号
	 * */
	private Integer siteId = 0;
	/*
	 * 周期会议设置ID
	 * */
	private Integer cycleId = 0;
	/*
	 * 会议名称
	 * */
	private String confName;
	/*
	 * 会议描述
	 * */
	private String confDesc;
	
	 
	
	/*
	 * 会议功能：
	 * 		0、数据会议功能（页面什么都不勾选）
	 * 		1 、电话功能
	 * 		2、视频功能 
	 * 		3、视频功能 + 电话功能
	 * */
	private Integer confType = 0;
	/*
	 * 会议开始时间
	 * */
	private Date startTime;
	/*
	 * 会议时间长度，分钟 为单位
	 * */
	private Integer duration;

	
	/*
	 * 会议结束时间
	 * */
	private Date endTime;
	/*
	 * 主持人ID号
	 * */
	private Integer compereUser;
	/*
	 * 主持人姓名
	 * */
	private String compereName;
	 
	
	 
	/*
	 * 电话会议号
	 * */
	private String callPhone = "";
	/*
	 * 电话会议密码
	 * */
	private String phonePass = "";
	/*
	 * 会议最大人数
	 * */
	private Integer maxUser = 0;
	 
  
	 
	 
	
	
	
	 
	 
	 
	/*
	 * 创建时间
	 * */
	private Date createTime = DateUtil.getGmtDate(null);
	/*
	 * 创建者ID号
	 * */
	private Integer createUser;
	/*
	 * 创建会议者类别
	 * 		0：无效数据
	 * 		1、站点管理员
	 * 		2、普通（企业）用户
	 * 		999、系统管理员
	 * */
	private Integer createType = 2;
	/*
	 * 删除标志 ： 
	 * 		0：无效数据  
	 * 		1、正常使用  
	 * 		2、已删除
	 * */
	private Integer delFlag = 1;
	/*
	 * 删除时间
	 * */
	private Date delTime;
	/*
	 * 删除用户的ID号
	 * */
	private Integer delUser = 0;
	/*
	 * 删除用户的类别
	 * 		0：无效数据
	 * 		1、站点管理员
	 * 		2、普通（企业）用户
	 * 		999、系统管理员
	 * */
	private Integer delType = 2;
	
	
	/*
	 * 站点时区
	 */
	private Integer timeZone;
	
	/**
	 * 站点时区ID
	 */
	private Integer timeZoneId;
	
	 
	
	/**
	 * 主持人密码
	 */
	private String hostKey = "";
	/**
	 * 是否永久会议
	 */
	private Integer permanentConf = 0; //0：非永久会议    1:永久主会议 2:永久会议子会议
	
	
	
	/*
	 * 会议状态
	 * 		0：无效状态
	 * 		1、预约成功
	 * 		2、正在召开
	 * 		3、已结束
	 * 		4、已过期
	 * 		5、取消的会议
	 * 		6、锁定
	 * */
	private Object confStatus = ConfStatus.SCHEDULED.getStatus();
	

	/*
	 * 对应zoom会议ID号
	 * */
	private String confZoomId;

	
	private  Date actulStartTime;
	

	
	// Constructors

	/** default constructor */
	public ConfBaseSimple() {
	}

	public ConfBaseSimple(Integer id, String confHwid, Integer siteId,
			Integer cycleId, String confName, String confDesc,
			Integer publicFlag, String publicConfPass, Integer confType,
			Date startTime, Integer duration, Date endTime,
			Integer compereUser, String compereName, String compereSecure,
			String userSecure, String cryptKey, String callPhone,
			String phonePass, Integer maxUser, Integer maxAudio,
			Integer maxVideo, String videoType, String maxDpi,
			String defaultDpi, Integer aheadTime, Integer openIpad,
			String funcBits, String priviBits, String clientConfig,
			Integer soapStatus, Integer confVersion, Date createTime,
			Integer createUser, Integer createType, Integer delFlag,
			Date delTime, Integer delUser, Integer delType, Integer timeZone,
			Integer timeZoneId, Integer pcNum, Integer phoneNum,
			String hostKey, Integer permanentConf, Integer belongConfId,
			Object confStatus) {
		super();
		this.id = id;
		 
		this.siteId = siteId;
		this.cycleId = cycleId;
		this.confName = confName;
		this.confDesc = confDesc;
		 
		this.confType = confType;
		this.startTime = startTime;
		this.duration = duration;
		this.endTime = endTime;
		this.compereUser = compereUser;
		this.compereName = compereName;
		 
		this.callPhone = callPhone;
		this.phonePass = phonePass;
		this.maxUser = maxUser;
		 
		this.createTime = createTime;
		this.createUser = createUser;
		this.createType = createType;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
		this.delType = delType;
		this.timeZone = timeZone;
		this.timeZoneId = timeZoneId;
		 
		this.hostKey = hostKey;
		this.permanentConf = permanentConf;
		 
		this.confStatus = confStatus;
	}

	public String getConfZoomId() {
		return confZoomId;
	}

	public void setConfZoomId(String confZoomId) {
		this.confZoomId = confZoomId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

	public String getConfDesc() {
		return confDesc;
	}

	public void setConfDesc(String confDesc) {
		this.confDesc = confDesc;
	}

	 
	public Integer getConfType() {
		return confType;
	}

	public void setConfType(Integer confType) {
		this.confType = confType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getCompereUser() {
		return compereUser;
	}

	public void setCompereUser(Integer compereUser) {
		this.compereUser = compereUser;
	}

	public String getCompereName() {
		return compereName;
	}

	public void setCompereName(String compereName) {
		this.compereName = compereName;
	}

	 
	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public String getPhonePass() {
		return phonePass;
	}

	public void setPhonePass(String phonePass) {
		this.phonePass = phonePass;
	}

	public Integer getMaxUser() {
		return maxUser;
	}

	public void setMaxUser(Integer maxUser) {
		this.maxUser = maxUser;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getCreateType() {
		return createType;
	}

	public void setCreateType(Integer createType) {
		this.createType = createType;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public Integer getDelUser() {
		return delUser;
	}

	public void setDelUser(Integer delUser) {
		this.delUser = delUser;
	}

	public Integer getDelType() {
		return delType;
	}

	public void setDelType(Integer delType) {
		this.delType = delType;
	}

	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(Integer timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	 

	public String getHostKey() {
		return hostKey;
	}

	public void setHostKey(String hostKey) {
		this.hostKey = hostKey;
	}

	public Integer getPermanentConf() {
		return permanentConf;
	}

	public void setPermanentConf(Integer permanentConf) {
		this.permanentConf = permanentConf;
	}

	 

	public Object getConfStatus() {
		return confStatus;
	}

	public void setConfStatus(Object confStatus) {
		this.confStatus = confStatus;
	}
	
	

	public Date getActulStartTime() {
		return actulStartTime;
	}

	public void setActulStartTime(Date actulStartTime) {
		this.actulStartTime = actulStartTime;
	}

	@Override
	public String toString() {
		return "ConfBaseSimple [id=" + id + ", siteId=" + siteId + ", cycleId="
				+ cycleId + ", confName=" + confName + ", confDesc=" + confDesc
				+ ", confType=" + confType + ", startTime=" + startTime
				+ ", duration=" + duration + ", endTime=" + endTime
				+ ", compereUser=" + compereUser + ", compereName="
				+ compereName + ", callPhone=" + callPhone + ", phonePass="
				+ phonePass + ", maxUser=" + maxUser + ", createTime="
				+ createTime + ", createUser=" + createUser + ", createType="
				+ createType + ", delFlag=" + delFlag + ", delTime=" + delTime
				+ ", delUser=" + delUser + ", delType=" + delType
				+ ", timeZone=" + timeZone + ", timeZoneId=" + timeZoneId
				+ ", hostKey=" + hostKey + ", permanentConf=" + permanentConf
				+ ", confStatus=" + confStatus + ", confZoomId=" + confZoomId
				+ ", actulStartTime=" + actulStartTime + "]";
	}




}