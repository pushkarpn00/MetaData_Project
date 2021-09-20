package com.tvu.Metadata_BE.dto;

public class ResponseDTO {
	
	private String ErrorCode;
	private String ErrorMessage;
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

}
