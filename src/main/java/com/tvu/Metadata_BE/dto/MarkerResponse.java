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

public class MarkerResponse {
	
	private List<MarkerInfo> markerinfo;
	private String errorCode;
	private String errorMessage;
	public List<MarkerInfo> getMarkerinfo() {
		return markerinfo;
	}
	public void setMarkerinfo(List<MarkerInfo> markerinfo) {
		this.markerinfo = markerinfo;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
