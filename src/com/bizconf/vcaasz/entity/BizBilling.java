package com.bizconf.vcaasz.entity;

import java.util.Date;

/**
 * @desc 
 * @author martin
 * @date 2013-5-20
 */
public class BizBilling {
	//主键ID
	private int id;
	//企业标志
	private String siteSign; 
	private String confHwid = ""; //会议号   月总账单 和固定费用账单为空
	private String userName = "";//参会者名称  月总账单 和固定费用账单为空
	private int enterType = 0;//进入方式 0:月账单 和固定费用账单 1:pc 2:phone  默认 2
	private String enterNumber = "";//接入号码
	private String callNumber = "";//拨打号码
	/**
	 * 0:月账单 或者固定费用账单   default
	 * Shrine_BC_Dail Out: 1
	 * Shrine_BC_Toll Free dail in:2
	 * Shrine_BC_Local conect Dail  In:3
	 * Shrine_BC_Playback:4
	 */
	private int eventType = 0 ; //事件类型   
	
	
	private Date startDate;//开始进入时间  
	
	private int duration = 0;//时长  默认为0  单位：s
	//private double totalFee = 0d; //金额   默认值0.00
	//账单记录类型 0:time cdr账单 1:固费型账单  2:每月总账单
	private int billType = 0;
	private String userId = "";//如果为namehost time模式 表示这个用户唯一标识 否则为空
	private Date billDate; //账单日期
	private Date createDate; //账单生成日期  
	
	//数据费用   billtype为1或者2(每月总账单)时设置
	private double dataFee = 0d;
	
	//通信费用   billtype为0或者2时设置
	private double telFee = 0d;
	
	//用于时长的显示
	public String getShowDuration(){
		if(this.duration!=0){
			StringBuilder showBuilder = new StringBuilder("");
			int hour = this.duration/3600;
			if(hour<10){
				showBuilder.append("0");
			}
			showBuilder.append(hour);
			showBuilder.append(":");
			
			int min = (this.duration%3600)/60;
			if(min<10){
				showBuilder.append("0");
			}
			showBuilder.append(min);
			showBuilder.append(":");
			
			int sec = (this.duration%3600)%60;
			if(sec<10){
				showBuilder.append("0");
			}
			showBuilder.append(sec);
			return showBuilder.toString();
		}else{
			return "00:00:00";
		}
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConfHwid() {
		return confHwid;
	}
	public void setConfHwid(String confHwid) {
		this.confHwid = confHwid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getEnterType() {
		return enterType;
	}
	public void setEnterType(int enterType) {
		this.enterType = enterType;
	}
	public String getEnterNumber() {
		return enterNumber;
	}
	public void setEnterNumber(String enterNumber) {
		this.enterNumber = enterNumber;
	}
	public String getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getTotalFee() {
		return this.dataFee+this.telFee;
	}
	
//	public void setTotalFee(double totalFee) {
//		this.totalFee = totalFee;
//	}

	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}
	
	
	public String getSiteSign() {
		return siteSign;
	}
	public void setSiteSign(String siteSign) {
		this.siteSign = siteSign;
	}
	 
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	
	public double getDataFee() {
		return dataFee;
	}
	
//	public void setDataFee(Object dataFee) {
//		if(dataFee!=null){
//			this.dataFee = Float.parseFloat(dataFee.toString());
//		}
//	}
	
	public void setDataFee(double dataFee) {
			this.dataFee = dataFee;
	}

	public double getTelFee() {
		return telFee;
	}

//	public void setTelFee(float telFee) {
//		this.telFee = telFee;
//	}
	
	public void setTelFee(double telFee) {
			this.telFee = telFee;
	}
	
//	public void setTelFee(Object telFee) {
//		if(telFee!=null){
//			this.telFee = Float.parseFloat(telFee.toString()) ;
//		}
//	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BizBilling [id=" + id + ", siteSign=" + siteSign
				+ ", confHwid=" + confHwid + ", userName=" + userName
				+ ", enterType=" + enterType + ", enterNumber=" + enterNumber
				+ ", callNumber=" + callNumber + ", eventType=" + eventType
				+ ", startDate=" + startDate + ", duration=" + duration
				+ ", billType=" + billType + ", userId="
				+ userId + ", billDate=" + billDate + ", createDate="
				+ createDate + ", dataFee=" + dataFee + ", telFee=" + telFee
				+ "]";
	}
	
	
	
}
