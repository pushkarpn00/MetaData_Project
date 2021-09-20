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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "downloadtask")
public class DownloadTask {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="taskid")
	private String taskid;
	@Column(name="userid")
	private String userid;
	@Column(name="sessionid")
	private String sessionid;
	@Column(name="sourceid")
	private String sourceid;
	@Column(name="recordid")
	private String recordid;
	@Column(name="keepduration")
	private Integer keepduration;
	@Column(name="filename")
	private String filename;
	@Column(name="Status")
	private Integer Status;
	@Column(name="createtime")
	private Long createtime;
	@Column(name="mergedtime")
	private Long mergedtime;
	@Column(name="deletetime")
	private Long deletetime;
	@Column(name="starttimestamp")
	private Long starttime;
	@Column(name="endtimestamp")
	private Long endtime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String string) {
		this.sessionid = string;
	}
	
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
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
	public void setKeepduration(Integer i) {
		this.keepduration = i;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public Long getMergedtime() {
		return mergedtime;
	}
	public void setMergedtime(Long mergedtime) {
		this.mergedtime = mergedtime;
	}
	public Long getDeletetime() {
		return deletetime;
	}
	public void setDeletetime(Long deletetime) {
		this.deletetime = deletetime;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtimestamp) {
		this.endtime = endtimestamp;
	}


}
