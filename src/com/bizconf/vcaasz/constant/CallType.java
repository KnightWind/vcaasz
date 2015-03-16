package com.bizconf.vcaasz.constant;

/** 
 *   
 * @package com.bizconf.vcaasz.constant 
 * @description: 账单的呼叫类型
 * @author Martin
 * @date 2014年12月2日 上午11:15:11 
 * @version 
 */
public enum CallType {
	
	DAILIN(1,"di"), DAILOUT(2,"do"), VIDEO(3,"vd");
	
	
	private int status;

	private String statusString;
	
	CallType(int statu,String statuString){
		this.status = statu;
		this.statusString = statuString;
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
	
	public static int getStatu(String statuString){
		statuString = statuString.toLowerCase();
		CallType[] callTypes = CallType.values();
		for (int i = 0; i < callTypes.length; i++) {
			CallType ct = callTypes[i];
			
			if(ct.getStatusString().equals(statuString)){
				return ct.getStatus();
			}
		}
		return VIDEO.getStatus();
	}
	
}
