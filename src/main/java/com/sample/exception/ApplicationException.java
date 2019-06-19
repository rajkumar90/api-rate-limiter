package com.sample.exception;

public class ApplicationException extends Exception {
	private ErrorCode errorCode;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1154575798019201136L;

	public ApplicationException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public ApplicationException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;		
	}
	
	public ApplicationException(ErrorCode errorCode, String message, Exception e) {
		super(message, e);
		this.errorCode = errorCode;
	}
}
