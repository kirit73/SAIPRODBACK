package com.sai.inventory.exception;

public class ErrorDetails {

	private String message;
	private int errorCode;
	private int errorTypeCode;
	private String errorType;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public int getErrorTypeCode() {
		return errorTypeCode;
	}
	public void setErrorTypeCode(int errorTypeCode) {
		this.errorTypeCode = errorTypeCode;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	@Override
	public String toString() {
		return "ErrorDetails [message=" + message + ", errorCode=" + errorCode + ", errorTypeCode=" + errorTypeCode
				+ ", errorType=" + errorType + "]";
	}
}
