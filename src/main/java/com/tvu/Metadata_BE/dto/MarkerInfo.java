/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.dto;

public class MarkerInfo {
	
	private String id;
	private String title;
	private String shmName;
	private Long starttimestamp;
	private Long endtimestamp;
	private String size;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShmName() {
		return shmName;
	}
	public void setShmName(String shmName) {
		this.shmName = shmName;
	}
	public Long getStarttimestamp() {
		return starttimestamp;
	}
	public void setStarttimestamp(Long starttimestamp) {
		this.starttimestamp = starttimestamp;
	}
	public Long getEndtimestamp() {
		return endtimestamp;
	}
	public void setEndtimestamp(Long endtimestamp) {
		this.endtimestamp = endtimestamp;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	

}
