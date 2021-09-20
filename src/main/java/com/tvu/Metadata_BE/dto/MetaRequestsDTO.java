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
public class MetaRequestsDTO {
	
	@JsonProperty("ID")
	private String Id;
	@JsonProperty("ProductionId")
	private Long productionID;
	@JsonProperty("ProgramID")
	private Long programID;
	@JsonProperty("ProgramName")
	private String programName;
	@JsonProperty("Title")
	private String Title;
	@JsonProperty("PlaceRootPath")
	private String PlaceRootPath;
	@JsonProperty("Description")
	private String Description;
	@JsonProperty("ProducedBy")
	private String ProducedBy;
	@JsonProperty("Status")
	private Integer Status;
	@JsonProperty("StartTime")
	private Long StartTime;
	@JsonProperty("EndTime")
	private Long EndTime;
	@JsonProperty("Sources")
	private List<SourcesDTO> Sources;
	@JsonProperty("ShortStories")
	private List<ShortStoriesDTO> ShortStories;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Long getProductionID() {
		return productionID;
	}
	public void setProductionID(Long productionID) {
		this.productionID = productionID;
	}
	public Long getProgramID() {
		return programID;
	}
	public void setProgramID(Long programID) {
		this.programID = programID;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getPlaceRootPath() {
		return PlaceRootPath;
	}
	public void setPlaceRootPath(String placeRootPath) {
		PlaceRootPath = placeRootPath;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getProducedBy() {
		return ProducedBy;
	}
	public void setProducedBy(String producedBy) {
		ProducedBy = producedBy;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public Long getStartTime() {
		return StartTime;
	}
	public void setStartTime(Long startTime) {
		StartTime = startTime;
	}
	public Long getEndTime() {
		return EndTime;
	}
	public void setEndTime(Long endTime) {
		EndTime = endTime;
	}
	public List<SourcesDTO> getSources() {
		return Sources;
	}
	public void setSources(List<SourcesDTO> sources) {
		Sources = sources;
	}
	public List<ShortStoriesDTO> getShortStories() {
		return ShortStories;
	}
	public void setShortStories(List<ShortStoriesDTO> shortStories) {
		ShortStories = shortStories;
	}

}
