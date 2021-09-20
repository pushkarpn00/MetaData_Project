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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "records")
public class Records implements java.io.Serializable{

	@Id
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sessionid")
	private Session session;
	@Column(name="sourceid")
	private String sourceId;
	@Column(name="title")
	private String Title;
	@Column(name="tag")
	private String Tags;
	@Column(name="starttime")
	private Long StartTimeStamp;
	@Column(name="endtime")
	private Long EndTimeStamp;


	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}

	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}


	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
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

}
