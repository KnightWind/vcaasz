package com.bizconf.vcaasz.exception;

/**
 * 创建会议时，超过最大并发会议数时抛出该异常
 * @author wangyong
 * 2013.7.23
 */
public class ConfSyncNumException extends RuntimeException {

	private static final long serialVersionUID = 87902214818050769L;
	
	private static final String message = "ConfSyncNum Exception!!!";
    
    public ConfSyncNumException(String message) {
        super(message);
    }

    public ConfSyncNumException() {
        super(message);
    }

    public ConfSyncNumException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfSyncNumException(Throwable cause) {
        super(message, cause);
    }

    public Throwable initCause(Throwable cause) {
        return cause;
    }
}
