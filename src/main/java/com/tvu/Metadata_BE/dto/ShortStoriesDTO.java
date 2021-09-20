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
public class ShortStoriesDTO {
	
	
	@JsonProperty("ID")
	private String ID;
	@JsonProperty("Title")
	private String Title;
	@JsonProperty("Tags")
	private List<String> Tags;
	@JsonProperty("Author")
	private String Author;
	@JsonProperty("CreatedAt")
	private Long CreatedAt;
	@JsonProperty("FrontCover")
	private String FrontCover;
	@JsonProperty("Scenes")
	private List<SceneDTO> Scenes;
	
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
	public String getAurthor() {
		return Author;
	}
	public void setAurthor(String author) {
		Author = author;
	}
	public Long getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Long createdAt) {
		CreatedAt = createdAt;
	}
	public String getFrontCover() {
		return FrontCover;
	}
	public void setFrontCover(String frontCover) {
		FrontCover = frontCover;
	}
	public List<SceneDTO> getScenes() {
		return Scenes;
	}
	public void setScenes(List<SceneDTO> scenes) {
		Scenes = scenes;
	}

}
