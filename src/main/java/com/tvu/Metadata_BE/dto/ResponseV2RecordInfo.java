package com.tvu.Metadata_BE.dto;

import java.util.List;

public class ResponseV2RecordInfo {
	private String Sessionid;
	private String Sourceid;
	private String ErrorCode;
	private String ErrorMessage;
	private List<RecordInfoRecordDTO> recordinfo;
	public String getSessionid() {
		return Sessionid;
	}
	public void setSessionid(String sessionid) {
		Sessionid = sessionid;
	}
	public String getSourceid() {
		return Sourceid;
	}
	public void setSourceid(String sourceid) {
		Sourceid = sourceid;
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
	public List<RecordInfoRecordDTO> getRecordinfo() {
		return recordinfo;
	}
	public void setRecordinfo(List<RecordInfoRecordDTO> recordinfo) {
		this.recordinfo = recordinfo;
	}

}
