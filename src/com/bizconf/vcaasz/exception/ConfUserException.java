package com.bizconf.vcaasz.exception;

public class ConfUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String message = "ConfUserService Unknown Exception!!!";
    
    public ConfUserException(String message) {
        super(message);
    }

    public ConfUserException() {
        super(message);
    }

    public ConfUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfUserException(Throwable cause) {
        super(message, cause);
    }

    public Throwable initCause(Throwable cause) {
        return cause;
    }
}
