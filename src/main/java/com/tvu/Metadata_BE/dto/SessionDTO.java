/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.dto;
public class SessionDTO implements java.io.Serializable{
	private String id;
	private Long programid;
	private Long productionid;
	private String programname;
	private String Title;
	private String Description;
	private String ProducedBy;
	private String Size;
	private Integer Status;
	private Long StartTime;
	private Long EndTime;
	private String PlaceRootPath;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getProgramid() {
		return programid;
	}
	public void setProgramid(Long programid) {
		this.programid = programid;
	}
	public Long getProductionid() {
		return productionid;
	}
	public void setProductionid(Long productionid) {
		this.productionid = productionid;
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
		return ProducedBy;
	}
	public void setProducedBy(String producedBy) {
		ProducedBy = producedBy;
	}
	public void setStatus(Integer integer) {
		Status = integer;
	}

	public Long getStartTime() {
		return StartTime;
	}
	public void setStartTime(Long long1) {
		StartTime = long1;
	}

	public Long getEndTime() {
		return EndTime;
	}
	public void setEndTime(Long date) {
		EndTime = date;
	}
	public String getPlaceRootPath() {
		return PlaceRootPath;
	}
	public void setPlaceRootPath(String placeRootPath) {
		PlaceRootPath = placeRootPath;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
}
