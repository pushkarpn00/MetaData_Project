package com.tvu.Metadata_BE.dto;

import java.util.List;

public class RecordInfoRecordDTO {
	
	private String ID;
	private String Title;
	private List<String> Tags;
	private Long StartTimeStamp;
	private Long EndTimeStamp;
	private String Size;
	private List<TaskDTO> tasks;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public List<String> getTags() {
		return Tags;
	}
	public void setTags(List<String> tags) {
		Tags = tags;
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
	public List<TaskDTO> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}
	
}
