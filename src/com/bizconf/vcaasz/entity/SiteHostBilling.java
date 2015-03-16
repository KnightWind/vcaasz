package com.bizconf.vcaasz.entity;

/** 
 *  
 * @package com.bizconf.vcaasz.entity 
 * @description: 企业管理员查看主持人账单详情账单对象
 * @author Martin
 * @date 2014年11月11日 下午2:25:39 
 * @version 
 */
public class SiteHostBilling {
	
	//账单所属的主持人
	private UserBase host;
	
	//月固定费用
	private double fixedCharge;
	
	//电话费用
	private double telCharge;
	
	//电话服务分钟数
	private int telMin;
	
	//视频计时费用
	private double videoCharge;
	
	//视频计时时长
	private int videoMin;
	
	//额外端口费用
	private double extraPortCharge;
	
	//额外端口数量
	private int extraPortNum;

	public UserBase getHost() {
		return host;
	}

	public void setHost(UserBase host) {
		this.host = host;
	}

	public double getFixedCharge() {
		return fixedCharge;
	}

	public void setFixedCharge(double fixedCharge) {
		this.fixedCharge = fixedCharge;
	}

	public double getTelCharge() {
		return telCharge;
	}

	public void setTelCharge(double telCharge) {
		this.telCharge = telCharge;
	}

	public int getTelMin() {
		return telMin;
	}

	public void setTelMin(int telMin) {
		this.telMin = telMin;
	}

	public double getVideoCharge() {
		return videoCharge;
	}

	public void setVideoCharge(double videoCharge) {
		this.videoCharge = videoCharge;
	}

	public int getVideoMin() {
		return videoMin;
	}

	public void setVideoMin(int videoMin) {
		this.videoMin = videoMin;
	}

	public double getExtraPortCharge() {
		return extraPortCharge;
	}

	public void setExtraPortCharge(double extraPortCharge) {
		this.extraPortCharge = extraPortCharge;
	}

	public int getExtraPortNum() {
		return extraPortNum;
	}

	public void setExtraPortNum(int extraPortNum) {
		this.extraPortNum = extraPortNum;
	}
	
}
