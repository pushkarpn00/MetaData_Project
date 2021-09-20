/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.dto;

public class MarkerDTO {
	
	private String id;
	private String productionid;
	private String sourcesharedmemoryname;
	private String title;
	private Long starttimestamp;
	private Long endtimestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductionid() {
		return productionid;
	}
	public void setProductionid(String productionid) {
		this.productionid = productionid;
	}
	public String getSourcesharedmemoryname() {
		return sourcesharedmemoryname;
	}
	public void setSourcesharedmemoryname(String sourcesharedmemoryname) {
		this.sourcesharedmemoryname = sourcesharedmemoryname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

}
