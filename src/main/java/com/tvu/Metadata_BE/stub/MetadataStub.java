/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.stub;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.DownloadTask;
import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.Model.Scene;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.Model.ShortStories;
import com.tvu.Metadata_BE.Model.Sources;
import com.tvu.Metadata_BE.dto.MetaDataResponse;
import com.tvu.Metadata_BE.dto.MetaRequestsDTO;
import com.tvu.Metadata_BE.dto.RecordsDTO;
import com.tvu.Metadata_BE.dto.ResponseDTO;
import com.tvu.Metadata_BE.dto.SceneDTO;
import com.tvu.Metadata_BE.dto.ShortStoriesDTO;
import com.tvu.Metadata_BE.dto.SourcesDTO;
import com.tvu.Metadata_BE.repository.DownloadTaskRepository;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.SceneRepository;
import com.tvu.Metadata_BE.repository.SessionRepository;
import com.tvu.Metadata_BE.repository.ShortStoriesRepository;
import com.tvu.Metadata_BE.repository.SourceRepository;
import com.tvu.Metadata_BE.util.CommonUtility;


@Service
public class MetadataStub {
	public String delims=",";
	@Autowired
	private SessionRepository sessionrepo;
	@Autowired
	private RecordRepository recordrepo;
	@Autowired
	private SourceRepository sourcerepo;
	@Autowired
	private SceneRepository scenerepo;
	@Autowired
	private ShortStoriesRepository storyrepo;
	@Autowired
	DownloadTaskRepository downloadTaskRepo;
	@Autowired
	CommonUtility commonUtil;

	static Logger logger = Logger.getLogger(MetadataStub.class);

	/*
	 * Method to save
	 * session,record,short-stories
	 * and scene using API in Database
	 * */
	@Transactional
	public MetaDataResponse saveData(MetaRequestsDTO context)
	{
		logger.info("*****************saveData Method Stated***************************");
		MetaDataResponse response=new MetaDataResponse();
		Session sessionDTO=new Session();
		sessionDTO.setId(context.getId());
		sessionDTO.setProductionid(context.getProductionID());
		sessionDTO.setProgramid(context.getProgramID());
		sessionDTO.setProgramname(context.getProgramName());
		sessionDTO.setTitle(context.getTitle());
		sessionDTO.setProducedBy(context.getProducedBy());
		sessionDTO.setStatus(context.getStatus());
		sessionDTO.setPlaceRootPath(context.getPlaceRootPath());
		sessionDTO.setStartTime(context.getStartTime());
		sessionDTO.setEndTime(context.getEndTime());
		sessionDTO.setDescription(context.getDescription());
		try {
			sessionrepo.save(sessionDTO);
		} catch(ConstraintViolationException exp){
			logger.error("ConstraintViolationException occured in session save");
		}
		List<SourcesDTO> sources=context.getSources();
		if(!sources.isEmpty()){
			for(SourcesDTO source:sources)
			{
				List<RecordsDTO> records=source.getRecords();
				if(!records.isEmpty()){
					for(RecordsDTO record:records)
					{
						Records rec=new Records();
						rec.setId(record.getID());
						rec.setSession(sessionDTO);
						rec.setSourceId(source.getID());
						List<String> tags=record.getTags();
						rec.setTags(String.join(delims, tags));
						rec.setTitle(record.getTitle());
						rec.setStartTimeStamp(record.getStartTimeStamp());
						rec.setEndTimeStamp(record.getEndTimeStamp());
						try {
							recordrepo.save(rec);
						} catch(ConstraintViolationException exp){
							logger.error("ConstraintViolationException occured in record save");
						}
					}
				}
				Sources sourceData=new Sources();
				sourceData.setId(source.getID());
				sourceData.setName(source.getName());
				sourceData.setSession(sessionDTO);
				sourceData.setStatus(source.getStatus());
				sourceData.setStartTime(source.getStartTimeStamp());
				sourceData.setStopTime(source.getEndTimeStamp());
				try {
					sourcerepo.save(sourceData);
				} catch(ConstraintViolationException exp){
					logger.error("ConstraintViolationException occured in source save");
				}
			}
		}
		List<ShortStoriesDTO> shortStories=context.getShortStories();
		if(!shortStories.isEmpty()){
			for(ShortStoriesDTO story:shortStories)
			{
				ShortStories stories=new ShortStories();
				stories.setId(story.getID());
				stories.setAurthor(story.getAurthor());
				stories.setSession(sessionDTO);
				stories.setFrontCover(story.getFrontCover());
				List<String> tags=story.getTags();
				stories.setTag(String.join(delims, tags));
				stories.setTitle(story.getTitle());
				//stories.setSourceid(sourceData);;
				stories.setCreatedAt(story.getCreatedAt());
				try {
					storyrepo.save(stories);
				} catch(ConstraintViolationException exp){
					logger.error("ConstraintViolationException occured in shortstories save");
				}
				List<SceneDTO> scenes=story.getScenes();
				if(!scenes.isEmpty()){
					for(SceneDTO scene:scenes)
					{
						Scene sceneData=new Scene();
						sceneData.setSourceid(scene.getAssociatedSource());
						sceneData.setShortStory(stories);
						sceneData.setStartTimeStamp(scene.getStartTimeStamp());
						sceneData.setStopTimeStamp(scene.getEndTimeStamp());
						try {
							scenerepo.save(sceneData);
						} catch(ConstraintViolationException exp){
							logger.error("ConstraintViolationException occured in shortstories save");
						}
					}
				}
			}
		}
		response.setErrorCode(Constants.API.SUCCESS_CODE);
		response.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
		logger.info("*****************saveData Method Done***************************");
		return response;

	}
	public ResponseDTO deleteWithSessionOrSource(String sessionid,String sourceid)
	{
		logger.info("*****************deleteWithSessionOrSource Method started***************************");
		ResponseDTO response=new ResponseDTO();
		try{
			Session sessionuseToDelete=sessionrepo.findById(sessionid).get();
			List<DownloadTask> downloadTask=downloadTaskRepo.findBySourceidOrSessionid(sourceid, sessionid);
			if(downloadTask.isEmpty()) {
				if(sessionid!=null && sourceid==null)
				{
					Session session=sessionrepo.findById(sessionid).get();
					if(session!=null && session.getStatus()==Constants.API.Merge)
					{
						session.setStatus(Constants.API.Delete);
						sessionrepo.save(session);
						new Thread(new Runnable() {
							public void run()
							{
								String cprefix = sessionuseToDelete.getPlaceRootPath().split("/")[1]+"/"+sessionuseToDelete.getProducedBy()+"/"+sessionid+"/";
								logger.info("AS:PREFIX is"+cprefix);
								commonUtil.deleteS3Object(sessionuseToDelete,cprefix,null);
							}

						}).start();
						response.setErrorCode(Constants.API.SUCCESS_CODE);
						response.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
					}
					else {
						logger.error("Given Sessionid status is incompleted or deleted ,so rejecting the request...");
						response.setErrorCode(Constants.API.ERROR_CODE);
						response.setErrorMessage(Constants.API.FAIL_MESSAGE);
					}
				}
				else if (sessionid!=null && sourceid!=null)
				{
					Sources source=sourcerepo.findBySessionidAndSourceid(sessionid, sourceid);
					if(source!=null && source.getStatus()==Constants.API.Merge )
					{
						source.setStatus(Constants.API.Delete);
						sourcerepo.save(source);
						new Thread(new Runnable() {
							public void run()
							{
								commonUtil.deleteS3Object(sessionuseToDelete, sessionuseToDelete.getPlaceRootPath().split("/")[1]+"/"+sessionuseToDelete.getProducedBy()+"/"+sessionid+"/"+source.getName()+"/",source);

							}

						}).start();
						response.setErrorCode(Constants.API.SUCCESS_CODE);
						response.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
					}
					else{
						logger.error("Given sourceid ,source not found in records or already deleted or incompleted");
						response.setErrorCode(Constants.API.ERROR_CODE);
						response.setErrorMessage(Constants.API.FAIL_MESSAGE);
					}
				}
			}
			else{
				response.setErrorCode(Constants.API.ERROR_CODE);
				response.setErrorMessage(Constants.API.FAIL_MESSAGE);
			}
		}catch(Exception e)
		{
			response.setErrorCode(Constants.API.ERROR_CODE);
			response.setErrorMessage(Constants.API.FAIL_MESSAGE);
			logger.error(e.getMessage());
		}
		logger.info("*****************deleteWithSessionOrSource Method Done***************************");
		return response;

	}
}
