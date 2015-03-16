package com.bizconf.vcaasz.constant;

/**
 * 会议状态定义
 * 
 * @author Chris Gao
 *
 */
public enum MeetingStartType {

	VIDEO(1, "video"), SCREEN_SHARE(2, "screen_share");

	private int status;

	private String statusString;

	MeetingStartType(int status, String statusString) {
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
	
	
	public static String  setStatusCode(int status) {
		MeetingStartType[] values = MeetingStartType.values();
		for (int i = 0; i < values.length; i++) {
			MeetingStartType type = values[i];
			if(type.getStatus() == status) {
				return type.getStatusString();
			}
		}
		return null;
	}

}
