package com.bizconf.vcaasz.constant;

/**
 * 会议状态定义
 * 
 * @author Chris Gao
 *
 */
public enum ConfStatus {

	INVALID(99, "无效状态"), SCHEDULED(0, "预约成功"), LIVING(2, "正在召开"), FINISHED(3,
			"已结束"), EXPIRED(4, "已过期"), CANCELED(9, "已取消"), LOCKED(11, "已锁定");

	private int status;

	private String statusString;

	ConfStatus(int status, String statusString) {
		this.status = status;
		this.statusString = statusString;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

}
