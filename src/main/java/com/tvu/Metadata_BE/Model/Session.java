/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.Model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "session")
public class Session implements java.io.Serializable{
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	@Column(name="productionid")
	private Long productionid;
	@Column(name="programid")
	private Long programid;
	@Column(name="title")
	private String Title;
	@Column(name="programname")
	private String programname;
	@Column(name="description")
	private String Description;
	@Column(name="producedby")
	private String producedBy;
	@Column(name="status")
	private Integer Status;
	@Column(name="starttime")
	private Long StartTime;
	@Column(name="endtime")
	private Long EndTime;
	@Column(name="placerootpath")
	private String PlaceRootPath;

	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,targetEntity=Sources.class)
	public Set<Sources> sources;
	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,targetEntity=Records.class)
	public Set<Records> records;
	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,targetEntity=ShortStories.class)
	public Set<ShortStories> shortStories;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Long getProductionid() {
		return productionid;
	}
	public void setProductionid(Long productionid) {
		this.productionid = productionid;
	}
	public Long getProgramid() {
		return programid;
	}
	public void setProgramid(Long programid) {
		this.programid = programid;
	}
	public String getProgramname() {
		return programname;
	}
	public void setProgramname(String programname) {
		this.programname = programname;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	public Integer getStatus() {
		return Status;
	}
	public String getProducedBy() {
		return producedBy;
	}
	public void setProducedBy(String producedBy) {
		this.producedBy = producedBy;
	}
	public void setStatus(Integer integer) {
		Status = integer;
	}

	public Long getStartTime() {
		return StartTime;
	}
	public void setStartTime(Long Long) {
		StartTime = Long;
	}

	public Long getEndTime() {
		return EndTime;
	}
	public void setEndTime(Long endTime) {
		EndTime = endTime;
	}
	public Set<Sources> getSources() {
		return sources;
	}
	public void setSources(Set<Sources> sources) {
		this.sources = sources;
	}
	public Set<Records> getRecords() {
		return records;
	}
	public void setRecords(Set<Records> records) {
		this.records = records;
	}
	public String getPlaceRootPath() {
		return PlaceRootPath;
	}
	public void setPlaceRootPath(String placeRootPath) {
		PlaceRootPath = placeRootPath;
	}
	public Set<ShortStories> getShortStories() {
		return shortStories;
	}
	public void setShortStories(Set<ShortStories> shortStories) {
		this.shortStories = shortStories;
	}

}
