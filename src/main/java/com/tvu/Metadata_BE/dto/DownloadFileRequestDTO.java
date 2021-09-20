/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.dto;
public class DownloadFileRequestDTO {

	private String userid;
	private String sessionid;
	private String recordid;
	private Integer keepduration;
	private Long StartTime;
	private Long EndTime;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public Integer getKeepduration() {
		return keepduration;
	}
	public void setKeepduration(Integer keepduration) {
		this.keepduration = keepduration;
	}
	public Long getStartTime() {
		return StartTime;
	}
	public void setStartTime(Long startTime) {
		StartTime = startTime;
	}
	public Long getEndTime() {
		return EndTime;
	}
	public void setEndTime(Long endTime) {
		EndTime = endTime;
	}
}
