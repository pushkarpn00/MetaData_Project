package com.tvu.Metadata_BE.dto;

import java.util.List;

public class SourcesResponse {
	private String Sessionid;
	private String ErrorCode;
	private String ErrorMessage;
	private List<SourceDTO> sourcesInfo;
	public String getSessionid() {
		return Sessionid;
	}
	public void setSessionid(String sessionid) {
		Sessionid = sessionid;
	}
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
	public List<SourceDTO> getSourcesInfo() {
		return sourcesInfo;
	}
	public void setSourcesInfo(List<SourceDTO> sourcesInfo) {
		this.sourcesInfo = sourcesInfo;
	}
}
