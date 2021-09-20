/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "marker")
public class Marker implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	@Column(name="title")
	private String Title;
	@Column(name="sourcesharedmemoryname")
	private String sourceSharedMemoryName;
	@Column(name="recordid")
	private String recordId;
	@Column(name="sessionid")
	private String sessionId;
	@Column(name="sourceid")
	private String sourceId;
	@Column(name="userid")
	private String userId;
	@Column(name="starttimestamp")
	private Long StartTimeStamp;
	@Column(name="endtimestamp")
	private Long EndTimeStamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getSourceSharedMemoryName() {
		return sourceSharedMemoryName;
	}
	public void setSourceSharedMemoryName(String sourceSharedMemoryName) {
		this.sourceSharedMemoryName = sourceSharedMemoryName;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getStartTimeStamp() {
		return StartTimeStamp;
	}
	public void setStartTimeStamp(Long startTimeStamp) {
		StartTimeStamp = startTimeStamp;
	}
	public Long getEndTimeStamp() {
		return EndTimeStamp;
	}
	public void setEndTimeStamp(Long endTimeStamp) {
		EndTimeStamp = endTimeStamp;
	}
	
}