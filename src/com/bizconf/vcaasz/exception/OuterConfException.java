package com.bizconf.vcaasz.exception;


/**
 * outer会议接口异常
 * @author wangyong
 * 2013.12.18
 */
public class OuterConfException extends RuntimeException {

	private static final long serialVersionUID = 2854942730110117646L;
	
	public String errorCode = "test";
	
    public OuterConfException(String errorCode) {
    	super(errorCode);
    	this.errorCode = errorCode;
    }
    
    public OuterConfException(){
    	super();
    }
    
    public String getErrorCode(){
    	return this.errorCode;
    }
}
