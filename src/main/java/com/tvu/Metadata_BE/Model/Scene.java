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
@Table(name = "scene")
public class Scene implements java.io.Serializable{
	/*@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storyid")
	private ShortStories shortStory;
	@Id
	@Column(name="sourceid")
	private String sourceid;
	@Column(name="starttime")
	private Long StartTimeStamp;
	@Column(name="endtime")
	private Long StopTimeStamp;


	/*public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}*/
	public ShortStories getShortStory() {
		return shortStory;
	}
	public void setShortStory(ShortStories shortStory) {
		this.shortStory = shortStory;
	}
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public Long getStartTimeStamp() {
		return StartTimeStamp;
	}
	public void setStartTimeStamp(Long Long) {
		StartTimeStamp = Long;
	}

	public Long getStopTimeStamp() {
		return StopTimeStamp;
	}
	public void setStopTimeStamp(Long stopTimeStamp) {
		StopTimeStamp = stopTimeStamp;
	}

}
