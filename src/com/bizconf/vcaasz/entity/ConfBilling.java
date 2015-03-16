package com.bizconf.vcaasz.entity;

import java.util.List;

/**
 * @desc 某个会议的通信费用总和
 * @author Darren
 * @date 2014-11-3
 */
public class ConfBilling {
	//对应的会议
//	private ConfBase conf;
	//参会会议记录
	private ZoomConfRecord zcr;
	//总金额
	private double sumCharge = 0;
	//时长（分钟）
	private int duration;
	//视频客户端账单
	private List<Billing> videoBillings;
	//电话账单
	private List<Billing> telBillings;
	
	
	public ZoomConfRecord getZcr() {
		return zcr;
	}
	public void setZcr(ZoomConfRecord zcr) {
		this.zcr = zcr;
	}
	public double getSumCharge() {
		return sumCharge;
	}
	public void setSumCharge(double sumCharge) {
		this.sumCharge = sumCharge;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<Billing> getVideoBillings() {
		return videoBillings;
	}
	public void setVideoBillings(List<Billing> videoBillings) {
		this.videoBillings = videoBillings;
	}
	public List<Billing> getTelBillings() {
		return telBillings;
	}
	public void setTelBillings(List<Billing> telBillings) {
		this.telBillings = telBillings;
	}
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
	
	@Override
	public String toString() {
		return "ConfBilling [zcr=" + zcr + ", sumCharge=" + sumCharge
				+ ", duration=" + duration + ", videoBillings=" + videoBillings
				+ ", telBillings=" + telBillings + "]";
	}
	
	
	
}
