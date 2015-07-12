package com.hexin.core.exception;

/**
 * 项目全局通用异常类
 *
 */
public class ErrorCodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ErrorCodeException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ErrorCodeException(Throwable cause) {
		super(cause);
	}
	
	public ErrorCodeException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String toMessage(){
		return "("+this.errorCode+")"+this.getMessage();
	}
}