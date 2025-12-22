package com.sai.inventory.domain.common.response;

public class ResponseStatus {

	private String message;
	private int statusCode;
	private int errorTypeCode;
	private String errorType;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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
		return "ResponseStatus [message=" + message + ", statusCode=" + statusCode + ", errorTypeCode=" + errorTypeCode
				+ ", errorType=" + errorType + "]";
	}
}
