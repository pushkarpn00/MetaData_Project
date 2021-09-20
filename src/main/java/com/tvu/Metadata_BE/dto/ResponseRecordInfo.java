/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.dto;
import java.util.List;

public class ResponseRecordInfo {
	private String Sessionid;
	private String ErrorCode;
	private String ErrorMessage;
	private List<RecordInfoDTO> recordinfo;
	
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
	public List<RecordInfoDTO> getRecordinfo() {
		return recordinfo;
	}
	public void setRecordinfo(List<RecordInfoDTO> recordinfo) {
		this.recordinfo = recordinfo;
	}
	
}
