/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.controller;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.Marker;
import com.tvu.Metadata_BE.dto.CreateDownloadTaskResponse;
import com.tvu.Metadata_BE.dto.DownloadFileRequestDTO;
import com.tvu.Metadata_BE.dto.DownloadFileResponseDTO;
import com.tvu.Metadata_BE.dto.MarkerDTO;
import com.tvu.Metadata_BE.dto.MarkerResponse;
import com.tvu.Metadata_BE.dto.MetaDataResponse;
import com.tvu.Metadata_BE.dto.MetaRequestsDTO;
import com.tvu.Metadata_BE.dto.ResponseDTO;
import com.tvu.Metadata_BE.dto.ResponseRecordInfo;
import com.tvu.Metadata_BE.dto.ResponseSessionInfo;
import com.tvu.Metadata_BE.dto.StatusResponse;
import com.tvu.Metadata_BE.stub.CreateTaskStub;
import com.tvu.Metadata_BE.stub.DownloadFileStub;
import com.tvu.Metadata_BE.stub.MarkerStub;
import com.tvu.Metadata_BE.stub.MetadataStub;
import com.tvu.Metadata_BE.stub.RecordStub;
import com.tvu.Metadata_BE.stub.SessionStub;
import com.tvu.Metadata_BE.stub.SourceStub;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "TVU Api for storing and retriving the MetaData Information..")
public class MetaDataController {
	@Autowired
	private MetadataStub savemetadata;
	@Autowired
	private RecordStub recordStub;
	@Autowired
	private SessionStub sessionStub;
	@Autowired
	private SourceStub sourceStub;
	@Autowired
	private DownloadFileStub downloadFileStub;
	@Autowired
	private CreateTaskStub createTaskStub;
	@Autowired
	private MarkerStub markerStub;

	static Logger logger = Logger.getLogger(MetaDataController.class);

	@ApiOperation(value = "To set the info of Meta Data ")
	@PostMapping(value = "/tvu/MeatadataService/setMetaData")
	public MetaDataResponse setMetaData(@RequestBody MetaRequestsDTO context)
			throws IOException {
		logger.info("Set Meta Data Called with request json : :"+new Gson().toJson(context));
		MetaDataResponse response=new MetaDataResponse();
		//System.out.println(context.toString());
		try {
			response=savemetadata.saveData(context);
		} catch(Exception e)	{
			System.out.println("exception occured"+e.toString());
			response.setErrorCode(Constants.API.SUCCESS_CODE);
			response.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
			System.out.println(response);
			logger.info("Set Meta Data Response json : :"+new Gson().toJson(response));
			return response;
		}
		logger.info("Set Meta Data Response json : :"+new Gson().toJson(response));
		return response;
	}

	@ApiOperation(value = "To Get SessionInfo")
	@GetMapping("/tvu/MeatadataService/GetSessionInfo")
	public ResponseSessionInfo getPart(@RequestParam(value = "userid") String userid,@RequestParam(value = "offset") int offset,@RequestParam(value = "limit") int limit,@RequestParam(value = "searchparam", required = false) String searchparam) {
		logger.info("Get Session Info API  request param : :"+"userid"+userid+"  offset"+offset+"  limit"+limit);
		ResponseSessionInfo response=sessionStub.getSessionInfo(userid,offset,limit,searchparam);
		logger.info("Get Session Info API Response"+new Gson().toJson(response));
		return response;
	}

	@ApiOperation(value = "To Get RecordInfo")
	@GetMapping("/tvu/MeatadataService/GetRecordInfo")
	public ResponseRecordInfo getRecords(@RequestParam(value = "sessionid") String sessionid) {
		logger.info("Get Record Info API  request param : :"+sessionid);
		ResponseRecordInfo recordinfo=recordStub.getRecordInfo(sessionid);
		logger.info("Get Record Info API  Response  : :"+new Gson().toJson(recordinfo));
		return recordinfo;
	}

	@GetMapping("/tvu/MeatadataService/download/{fileName}")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName)
	{
		logger.info("Download File API called with file  : :"+fileName);
		ResponseEntity response=downloadFileStub.getResponseEntiity(fileName);
		return response;
	}

	@ApiOperation(value = "Get the DownloadRequest and download link")
	@PostMapping(value = "/tvu/MeatadataService/createdownloadtask")
	public CreateDownloadTaskResponse downloadTaskResponse(@RequestBody(required = true) DownloadFileRequestDTO downloadrequest)
	{
		logger.info("Create Download task called request   : :"+downloadrequest);
		CreateDownloadTaskResponse response=new CreateDownloadTaskResponse();
		boolean isvalid=sessionStub.isvalidRequest(downloadrequest);
		logger.info("Is Create Download Request with valid parameter : :"+isvalid);
		if(isvalid)
		{

			DownloadFileResponseDTO downloadresponse=sessionStub.getParamsInfo(downloadrequest.getRecordid(),downloadrequest.getSessionid(),downloadrequest.getStartTime(),downloadrequest.getEndTime());
			boolean checkforexist=downloadFileStub.checkForTaskExist(downloadrequest.getRecordid(),downloadrequest.getSessionid(),downloadresponse);
			logger.info("Createdownload Request already exist with ready or inprogress : :"+checkforexist);
			if(checkforexist)
			{
				response=downloadFileStub.getTaskDetails(downloadrequest.getRecordid(),downloadrequest.getSessionid(),downloadresponse);
			}
			else
			{
				File directory = new File(downloadresponse.getDownloadpath());
				System.out.println(directory);
				if (!directory.exists()) {
					System.out.println("download folder created");
					directory.mkdirs();
					directory.setReadable(true,false);
					directory.setWritable(true,false);
				}else {
					System.out.println("download folder exist");
				}
				response=createTaskStub.asyncBackgroundWithTaskid(downloadrequest, downloadresponse);
			}
		}
		else
		{
			response.setErrorCode(Constants.API.ERROR_CODE);
			response.setErrorMessage(Constants.API.INVALID_PARAMETER);
		}
		logger.info("Create Download task Response   : :"+new Gson().toJson(response));
		return response;
	}

	@GetMapping("/tvu/MeatadataService/downloadtask/status")
	public StatusResponse value(@RequestParam(value = "taskid") String taskid)
	{
		logger.info("Download task status api called for taskid  : :"+taskid);
		return downloadFileStub.getstatus(taskid);
	}
	@DeleteMapping(value ="/tvu/MeatadataService/delete")
	public ResponseDTO deletesession(@RequestParam(value = "sessionid", required = true) String sessionid,@RequestParam(value = "sourceid", required = false) String sourceid) {
		logger.info("Delete api called session : :"+sessionid+" Source : :"+sourceid);
		return savemetadata.deleteWithSessionOrSource(sessionid, sourceid);
	}

	@ApiOperation(value = "Set value of marker in database")
	@PostMapping(value = "/tvu/MeatadataService/marker")
	public ResponseDTO setMarker(@RequestBody(required = true) MarkerDTO markerDTO)
	{
		logger.info("Saving Marker Details Started : :"+new Gson().toJson(markerDTO));
		return markerStub.saveMarker(markerDTO);
	}

	@GetMapping(value="/tvu/MeatadataService/marker"/*, params = {"sourceid","productionid"}*/)
	public MarkerResponse getMarkerBySourceOrProductionid(@RequestParam(value = "sourceid", required = false) String sourceid,@RequestParam(value = "productionid", required = false) String productionid)
	{
		logger.info("getMarkerBySourceid api called for sourceid  : :"+sourceid +"& productionid " +productionid);
		if(sourceid!=null){
			return markerStub.getMarkerbySourceid(sourceid);
		}else{
			return markerStub.getMarkerbyProductionid(productionid);
		}
	}
	@DeleteMapping(value ="/tvu/MeatadataService/marker")
	public ResponseDTO deleteMarker(@RequestParam(value = "markerid", required = true) String markerid) {
		logger.info("Delete api called for marker : :"+markerid);
		return markerStub.deleteMarker(markerid);
	}
	
	@GetMapping(value="/tvu/MeatadataService/marker/pagination")
	public List<Marker> getMarkerByPagination(@RequestParam(value = "pageNo", required = false) int pageNo, @RequestParam(value = "pageSize", required = false) int pageSize)
	{
		logger.info("getMarkerBySourceid api called for pageNo  : :"+pageNo +"& pageSize " +pageSize);
		if(pageNo >=0 && pageSize>=0 ){
			return markerStub.paginationMarker(pageNo, pageSize);
		}
		return null;
	}
}
