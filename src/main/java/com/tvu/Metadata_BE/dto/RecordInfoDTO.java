package com.tvu.Metadata_BE.dto;

import java.util.List;

public class RecordInfoDTO {
	private String ID;
	private String Name;
	private Integer Status;
	private Long StartTimeStamp;
	private Long EndTimeStamp;
	private String Size;
	private List<RecordInfoRecordDTO> records;
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
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public List<RecordInfoRecordDTO> getRecords() {
		return records;
	}
	public void setRecords(List<RecordInfoRecordDTO> records) {
		this.records = records;
	}
	
}
