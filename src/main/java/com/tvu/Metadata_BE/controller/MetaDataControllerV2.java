/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.controller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tvu.Metadata_BE.dto.ResponseV2RecordInfo;
import com.tvu.Metadata_BE.dto.SourcesResponse;
import com.tvu.Metadata_BE.stub.RecordStub;
import com.tvu.Metadata_BE.stub.SourceStub;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "TVU Api for storing and retriving the MetaData Information..")
public class MetaDataControllerV2 {
	
	@Autowired
	private RecordStub recordStub;
	@Autowired
	private SourceStub sourceStub;
	
	static Logger logger = Logger.getLogger(MetaDataControllerV2.class);

	@ApiOperation(value = "To Get List Of Sources")
	@GetMapping("/tvu/MeatadataService/v2/GetSources")
	public SourcesResponse getSources(@RequestParam(value = "sessionid") String sessionid) {
		logger.info("Get Sources details on basis of sessionid : :"+sessionid);
		SourcesResponse sources=sourceStub.getListSourceInfo(sessionid);
		logger.info("Get Sources details on basis of sessionid Response  : :"+new Gson().toJson(sources));
		return sources;
	}
	@ApiOperation(value = "To Get RecordInfo For Source")
	@GetMapping("/tvu/MeatadataService/v2/GetRecordInfo")
	public ResponseV2RecordInfo getV2Records(@RequestParam(value = "sessionid") String sessionid,@RequestParam(value = "sourceid") String sourceid) {
		logger.info("Get API request param : :Sessionid : :"+sessionid+"Source id : :"+sourceid);
		ResponseV2RecordInfo recordinfo=recordStub.getV2RecordInfo(sessionid, sourceid);
		logger.info("Get Record Info API  Response  : :"+new Gson().toJson(recordinfo));
		return recordinfo;
	}
}
