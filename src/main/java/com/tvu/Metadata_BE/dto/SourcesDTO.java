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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
public class SourcesDTO {
	
	@JsonProperty("ID")
	private String ID;
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("Status")
	private Integer Status;
	@JsonProperty("StartTimestamp")
	private Long StartTimeStamp;
	@JsonProperty("EndTimestamp")
	private Long EndTimeStamp;
	@JsonProperty("Records")
	private List<RecordsDTO> Records;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
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
	public List<RecordsDTO> getRecords() {
		return Records;
	}
	public void setRecords(List<RecordsDTO> records) {
		Records = records;
	}

}
