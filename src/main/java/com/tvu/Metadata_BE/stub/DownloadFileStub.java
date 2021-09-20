/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.stub;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.DownloadTask;
import com.tvu.Metadata_BE.dto.CreateDownloadTaskResponse;
import com.tvu.Metadata_BE.dto.DownloadFileResponseDTO;
import com.tvu.Metadata_BE.dto.StatusResponse;
import com.tvu.Metadata_BE.repository.DownloadTaskRepository;


@Service
public class DownloadFileStub {

	@Value("${user.path}")
	private String fileBasePath;

	@Autowired
	DownloadTaskRepository downloadRepo;

	static Logger logger = Logger.getLogger(DownloadFileStub.class);

	//Method to generate the download URL response from local Systems
	public ResponseEntity getResponseEntiity(String fileName)
	{
		logger.info("*****************getResponseEntiity Method stated*****************");
		String basepath = fileBasePath;
		System.out.println("Base Path: :"+basepath+"    "+"File Name: :"+fileName);
		logger.info("Base Path: :"+basepath+"    "+"File Name: :"+fileName);
		//String taskdir=fileName.substring(0, fileName.indexOf("."));
		System.out.println(basepath+"/"+fileName+"/"+fileName);
		Path path = Paths.get(basepath+"/"+fileName+"/"+fileName+".ts");
		UrlResource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			logger.error(e.toString());
		}
		logger.info("*****************getResponseEntiity Method Done*****************");
		return ResponseEntity.ok().
				contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	public boolean checkForTaskExist(String recordid,String sessionid,DownloadFileResponseDTO downloadresponse)
	{
		DownloadTask downloadtask=downloadRepo.getBySessionRecordStartAndEndTime(recordid,sessionid,Long.parseLong(downloadresponse.getStarttimestamp()),Long.parseLong(downloadresponse.getEndtimestamp()));
		if(downloadtask!=null)
		{
			return true;	
		}
		return false;

	}
	public CreateDownloadTaskResponse getTaskDetails(String recordid,String sessionid,DownloadFileResponseDTO downloadresponse)
	{
		CreateDownloadTaskResponse response=new CreateDownloadTaskResponse();
		DownloadTask downloadtask=downloadRepo.getBySessionRecordStartAndEndTime(recordid,sessionid,Long.parseLong(downloadresponse.getStarttimestamp()),Long.parseLong(downloadresponse.getEndtimestamp()));
		response.setErrorCode(Constants.API.SUCCESS_CODE);
		response.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
		response.setTaskid(downloadtask.getTaskid());
		response.setStatus(downloadtask.getStatus());
		return response;
	}

	public StatusResponse getstatus(String taskid)
	{
		StatusResponse response=new StatusResponse();
		DownloadTask download=null;

		try{
			download=downloadRepo.findByTaskid(taskid);	
			if(download!=null)
			{
				response.setErrorCode(Constants.API.SUCCESS_CODE);
				response.setTaskID(taskid);
				response.setErrorMessage(Constants.API.SUCCESS_CODE);
				response.setStatus(download.getStatus());		
			}else
			{
				response.setErrorCode(Constants.API.ERROR_CODE);
				response.setTaskID(taskid);
				response.setErrorMessage(Constants.API.TaskNOtFound);
				response.setStatus(Constants.API.taskidnotfound);

			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			response.setErrorCode(Constants.API.ERROR_CODE);
			response.setTaskID(taskid);
			response.setErrorMessage(Constants.API.FAIL_MESSAGE);
			response.setStatus(Constants.API.Failed);	
		}
		return response;
	}
}
