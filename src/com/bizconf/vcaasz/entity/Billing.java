package com.bizconf.vcaasz.entity;

import java.io.Serializable;
import java.util.Date;

import com.bizconf.vcaasz.component.language.LanguageHolder;

/**
 * @desc 账单
 * @author Darren
 * @date 2014-11-3
 */
@SuppressWarnings("serial")
public class Billing implements Serializable {

	private Integer id;
	/**
	 * 站点ID号
	 */
	private Integer siteId;
	/**
	 * 账单类型:1 视频计时;2 namehost包月;3 电话计时;4 namehost额外端口
	 * */
	private int billType;
	
	/**
	 * Namehost的id
	 * */
	private Integer hostId;
	
	/**
	 * 会议的recordId
	 * */
	private long recordId;
	
	/**
	 * 会议的ZoomId
	 * */
	private String confZoomId;
	
	/**
	 * 计费开始时间
	 * */
	private Date startTime;
	/**
	 * 计费结束时间
	 * */
	private Date endTime;
	
	/**
	 * 时长 (分钟)
	 * */
	private int duration;
	
	/**
	 * 参会者名称
	 * */
	private String name;
	
	/**
	 * 电话号码
	 * */
	private String phone;
	
	/**
	 * 呼叫类型：1 呼入；2 呼出；3 视频；
	 * */
	private int callType;
	
	/**
	 * 接入号码
	 * */
	private String dnis;
	
	/**
	 * 费用
	 * */
	private double charge;

	/**
	 * 主持人的参会方数
	 * */
	private int numParts;
	
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

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public Integer getHostId() {
		return hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public String getConfZoomId() {
		return confZoomId;
	}

	public void setConfZoomId(String confZoomId) {
		this.confZoomId = confZoomId;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCallType() {
		return callType;
	}

	public void setCallType(int callType) {
		this.callType = callType;
	}

	public String getDnis() {
		return dnis;
	}

	public void setDnis(String dnis) {
		this.dnis = dnis;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public int getNumParts() {
		return numParts;
	}

	public void setNumParts(int numParts) {
		this.numParts = numParts;
	}

	@Override
	public String toString() {
		return "Billing [id=" + id + ", siteId=" + siteId + ", bill_type="
				+ billType + ", hostId=" + hostId + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", Duration=" + duration + ", name="
				+ name + ", phone=" + phone + ", callType=" + callType
				+ ", dnis=" + dnis + ", charge=" + charge + "]";
	}
	
	
	public String getShowDuration(){
		String dur = "";
		int h = this.duration/60;
		int min = this.duration%60;
		if(LanguageHolder.getCurrentLanguage().startsWith("zh")){
			dur = h+" 小时 "+min+" 分";
		}else{
			dur = h+" hours "+min+" minutes";
		}
		return dur;
	}
}
