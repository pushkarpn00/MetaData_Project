/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.stub;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.DownloadTask;
import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.Model.Sources;
import com.tvu.Metadata_BE.dto.RecordInfoDTO;
import com.tvu.Metadata_BE.dto.RecordInfoRecordDTO;
import com.tvu.Metadata_BE.dto.ResponseRecordInfo;
import com.tvu.Metadata_BE.dto.ResponseV2RecordInfo;
import com.tvu.Metadata_BE.dto.TaskDTO;
import com.tvu.Metadata_BE.repository.DownloadTaskRepository;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.SourceRepository;
import com.tvu.Metadata_BE.util.CommonUtility;


@Service("recordStub")
public class RecordStub {

	@Autowired
	private RecordRepository recordInfo;
	@Autowired
	private DownloadTaskRepository downloadTaskRepository;
	@Autowired
	private SourceRepository sourceRepository;
	static Logger logger = Logger.getLogger(RecordStub.class);


	/*
	 * Method to get the Record Details
	 * */
	public ResponseRecordInfo getRecordInfo(String sessionid)
	{
		logger.info("*****************getRecordinfo Method Stated***************************");
		ResponseRecordInfo recordinfo=new ResponseRecordInfo();
		recordinfo.setSessionid(sessionid);
		List<Sources> listsource=sourceRepository.findBySessionidAndEndTime(sessionid);
		List<RecordInfoDTO> recordInfoDTOList=new ArrayList<RecordInfoDTO>();
		for(Sources source:listsource)
		{
			RecordInfoDTO recordInfoDTO=new RecordInfoDTO();
			recordInfoDTO.setID(source.getId());
			recordInfoDTO.setName(source.getName());
			recordInfoDTO.setStatus(source.getStatus());
			recordInfoDTO.setStartTimeStamp(source.getStartTime());
			recordInfoDTO.setEndTimeStamp(source.getStopTime());
			double value1=(source.getStopTime()-source.getStartTime());
			System.out.println(source.getStartTime()+"/"+source.getStopTime());
			recordInfoDTO.setSize(CommonUtility.calculatedTime(value1));
			List<Records> listRecords=recordInfo.getAllRecordBySourceidAndSessionIDAndEndtime(source.getId(), sessionid);
			List<RecordInfoRecordDTO> listRecordInforRecord=new ArrayList<RecordInfoRecordDTO>();
			for(Records record:listRecords)
			{
				RecordInfoRecordDTO recordInfoRecordDTO=new RecordInfoRecordDTO();
				recordInfoRecordDTO.setID(record.getId());
				recordInfoRecordDTO.setTitle(record.getTitle());
				String value=record.getTags();
				String tag[]=value.split(",");
				List<String> listValue=new ArrayList<>();
				listValue=Arrays.asList(tag);
				recordInfoRecordDTO.setTags(listValue);
				recordInfoRecordDTO.setStartTimeStamp(record.getStartTimeStamp());
				recordInfoRecordDTO.setEndTimeStamp(record.getEndTimeStamp());
				double Recvalue1=(record.getEndTimeStamp()-record.getStartTimeStamp());
				System.out.println(record.getStartTimeStamp()+"/"+record.getEndTimeStamp());
				recordInfoRecordDTO.setSize(CommonUtility.calculatedTime(Recvalue1));
				List<DownloadTask> downloadTasks=downloadTaskRepository.getDownloadByRecordAndSession(record.getId(), sessionid);
				List<TaskDTO> tasks=new ArrayList<TaskDTO>();
				if(downloadTasks!=null && !downloadTasks.isEmpty())
				{
					for(DownloadTask downloadTask:downloadTasks)
					{
						if(downloadTask.getStatus()!=null)
						{
							if(downloadTask.getStatus()==Constants.API.Merge)
							{
								TaskDTO task=new TaskDTO();
								task.setStartTimeStamp(downloadTask.getCreatetime());
								task.setEndTimeStamp(0L);
								task.setStatus(Constants.API.Merge);
								task.setTaskid(downloadTask.getTaskid());
								tasks.add(task);
							}
							else if(downloadTask.getStatus()==Constants.API.Ready)
							{
								TaskDTO task=new TaskDTO();
								task.setStartTimeStamp(downloadTask.getCreatetime());
								task.setEndTimeStamp(downloadTask.getMergedtime());
								task.setStatus(Constants.API.Ready);
								task.setTaskid(downloadTask.getTaskid());
								tasks.add(task);

							}
							else if(downloadTask.getStatus()==Constants.API.Failed)
							{
								TaskDTO task=new TaskDTO();
								task.setStartTimeStamp(downloadTask.getCreatetime());
								task.setEndTimeStamp(downloadTask.getMergedtime());
								task.setStatus(Constants.API.Failed);
								task.setTaskid(downloadTask.getTaskid());
								tasks.add(task);
							}
						}
					}
				}
				else
				{
					TaskDTO task=new TaskDTO();
					task.setStartTimeStamp(0L);
					task.setEndTimeStamp(0L);
					task.setStatus(Constants.API.Normal);
					task.setTaskid("");
					tasks.add(task);
				}
				recordInfoRecordDTO.setTasks(tasks);
				listRecordInforRecord.add(recordInfoRecordDTO);
			}
			recordInfoDTO.setRecords(listRecordInforRecord);
			recordInfoDTOList.add(recordInfoDTO);
		}
		recordinfo.setErrorCode(Constants.API.SUCCESS_CODE);
		recordinfo.setErrorMessage(Constants.API.SUCCESS_MESSAGE_GET);
		recordinfo.setRecordinfo(recordInfoDTOList);
		logger.info("*****************Recordinfo Method Done***************************");
		return recordinfo;
	}
	/*
	 * Method to get the V2 Record Details
	 * */
	public ResponseV2RecordInfo getV2RecordInfo(String sessionid,String sourceid)
	{
		logger.info("*****************getV2RecordInfo Method Stated***************************");
		ResponseV2RecordInfo v2recordinfo=new ResponseV2RecordInfo();
		v2recordinfo.setSessionid(sessionid);
		v2recordinfo.setSourceid(sourceid);
		List<Records> listRecords=recordInfo.getAllRecordBySourceidAndSessionIDAndEndtime(sourceid, sessionid);
		List<RecordInfoRecordDTO> listRecordInforRecord=new ArrayList<RecordInfoRecordDTO>();
		for(Records record:listRecords)
		{
			RecordInfoRecordDTO recordInfoRecordDTO=new RecordInfoRecordDTO();
			recordInfoRecordDTO.setID(record.getId());
			recordInfoRecordDTO.setTitle(record.getTitle());
			String value=record.getTags();
			String tag[]=value.split(",");
			List<String> listValue=new ArrayList<>();
			listValue=Arrays.asList(tag);
			recordInfoRecordDTO.setTags(listValue);
			recordInfoRecordDTO.setStartTimeStamp(record.getStartTimeStamp());
			recordInfoRecordDTO.setEndTimeStamp(record.getEndTimeStamp());
			double Recvalue1=(record.getEndTimeStamp()-record.getStartTimeStamp());
			System.out.println(record.getStartTimeStamp()+"/"+record.getEndTimeStamp());
			recordInfoRecordDTO.setSize(CommonUtility.calculatedTime(Recvalue1));
			List<DownloadTask> downloadTasks=downloadTaskRepository.getDownloadByRecordAndSession(record.getId(), sessionid);
			List<TaskDTO> tasks=new ArrayList<TaskDTO>();
			if(downloadTasks!=null && !downloadTasks.isEmpty())
			{
				for(DownloadTask downloadTask:downloadTasks)
				{
					if(downloadTask.getStatus()!=null)
					{
						if(downloadTask.getStatus()==Constants.API.Merge)
						{
							TaskDTO task=new TaskDTO();
							task.setStartTimeStamp(downloadTask.getCreatetime());
							task.setEndTimeStamp(0L);
							task.setStatus(Constants.API.Merge);
							task.setTaskid(downloadTask.getTaskid());
							tasks.add(task);
						}
						else if(downloadTask.getStatus()==Constants.API.Ready)
						{
							TaskDTO task=new TaskDTO();
							task.setStartTimeStamp(downloadTask.getCreatetime());
							task.setEndTimeStamp(downloadTask.getMergedtime());
							task.setStatus(Constants.API.Ready);
							task.setTaskid(downloadTask.getTaskid());
							tasks.add(task);

						}
						else if(downloadTask.getStatus()==Constants.API.Failed)
						{
							TaskDTO task=new TaskDTO();
							task.setStartTimeStamp(downloadTask.getCreatetime());
							task.setEndTimeStamp(downloadTask.getMergedtime());
							task.setStatus(Constants.API.Failed);
							task.setTaskid(downloadTask.getTaskid());
							tasks.add(task);
						}
					}
				}
			}
			else
			{
				TaskDTO task=new TaskDTO();
				task.setStartTimeStamp(0L);
				task.setEndTimeStamp(0L);
				task.setStatus(Constants.API.Normal);
				task.setTaskid("");
				tasks.add(task);
			}
			recordInfoRecordDTO.setTasks(tasks);
			listRecordInforRecord.add(recordInfoRecordDTO);
		}
		v2recordinfo.setErrorCode(Constants.API.SUCCESS_CODE);
		v2recordinfo.setErrorMessage(Constants.API.SUCCESS_MESSAGE_GET);
		v2recordinfo.setRecordinfo(listRecordInforRecord);
		logger.info("*****************getV2RecordInfo Method Done***************************");
		return v2recordinfo;
	}
}

