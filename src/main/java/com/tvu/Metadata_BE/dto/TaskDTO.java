package com.tvu.Metadata_BE.dto;

public class TaskDTO {
	private String Taskid;
	private Integer Status;
	private Long StartTimeStamp;
	private Long EndTimeStamp;
	public String getTaskid() {
		return Taskid;
	}
	public void setTaskid(String taskid) {
		Taskid = taskid;
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
}
