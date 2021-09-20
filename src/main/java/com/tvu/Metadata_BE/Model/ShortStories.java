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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shortstories")
public class ShortStories implements java.io.Serializable{
	
	@Id
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sessionid")
	private Session session;
	/*@Column(name = "sourceid")
	private String sourceid;*/
	@Column(name="title")
	private String Title;
	@Column(name="tags")
	private String Tag;
	@Column(name="frontcover")
	private String FrontCover;
	@Column(name="author")
	private String Author;
	@Column(name="createdat")
	private Long CreatedAt;
	@OneToMany(mappedBy = "shortStory", cascade = CascadeType.ALL,
		    fetch = FetchType.LAZY)
	public Set<Scene> scenes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	
	public String getFrontCover() {
		return FrontCover;
	}
	public void setFrontCover(String frontCover) {
		FrontCover = frontCover;
	}
	
	public String getAurthor() {
		return Author;
	}
	public void setAurthor(String author) {
		Author = author;
	}
	/*
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}*/
	public Long getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Long Long) {
		CreatedAt = Long;
	}
	public Set<Scene> getScenes() {
		return scenes;
	}
	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}
}
