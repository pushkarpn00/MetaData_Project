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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.Model.Sources;
import com.tvu.Metadata_BE.dto.DownloadFileRequestDTO;
import com.tvu.Metadata_BE.dto.DownloadFileResponseDTO;
import com.tvu.Metadata_BE.dto.ResponseSessionInfo;
import com.tvu.Metadata_BE.dto.SessionDTO;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.SessionRepository;
import com.tvu.Metadata_BE.repository.SourceRepository;
import com.tvu.Metadata_BE.util.CommonUtility;


@Service("SessionStub")
public class SessionStub {

	@Autowired
	private SourceRepository sourcerepo;
	@Autowired
	private RecordRepository recordrepo;
	@Autowired
	private SessionRepository sessionrepo;

	@Value("${user.path}")
	private String userPath;
	@Value("${user.cmdpath}")
	private String commandPath;
	@Value("${redis.ip}")
	private String redisip;
	@Value("${redis.port}")
	private String port;
	@Value("${redis.password}")
	private String password;

	static Logger logger = Logger.getLogger(SessionStub.class);


	/*
	 * Method to get the Session Details
	 * */
	public ResponseSessionInfo getSessionInfo(String userid,int offset,int limit,String searchparam)
	{
		logger.info("*****************getSessionInfo Method Stated***************************");
		ResponseSessionInfo sessioninfo=new ResponseSessionInfo();
		int countvalue;
		List<Session> sessions=new ArrayList<>();
		List<Session> countSession=new ArrayList<>();
		List<Session> count=sessionrepo.findUsingProducedBy(userid);
		if(searchparam!=null && !searchparam.isEmpty()) {
			sessions=sessionrepo.getSessionByProducedByWithSearch(userid,offset,limit,searchparam);
			countSession=sessionrepo.getcount(userid, searchparam);
			countvalue=countSession.size();
		}
		else
		{
			sessions=sessionrepo.getSessionByProducedBy(userid,offset,limit);
			countvalue=count.size();
		}

		List<SessionDTO> listSession=new ArrayList<SessionDTO>();
		for(Session s:sessions)
		{
			SessionDTO session=new SessionDTO();
			session.setId(s.getId());
			session.setProgramid(s.getProgramid());
			session.setProductionid(s.getProductionid());
			session.setProgramname(s.getProgramname());
			session.setPlaceRootPath(s.getPlaceRootPath());
			session.setProducedBy(s.getProducedBy());
			session.setTitle(s.getTitle());
			session.setDescription(s.getDescription());
			session.setStatus(s.getStatus());
			double value1=(s.getEndTime()-s.getStartTime());
			System.out.println(s.getStartTime()+"/"+s.getEndTime());
			session.setSize(CommonUtility.calculatedTime(value1));
			session.setStartTime(s.getStartTime());
			session.setEndTime(s.getEndTime());
			listSession.add(session);
		}
		sessioninfo.setTotal_count(count.size());
		sessioninfo.setCount(countvalue);
		sessioninfo.setErrorCode(Constants.API.SUCCESS_CODE);
		sessioninfo.setErrorMessage(Constants.API.SUCCESS_MESSAGE_GET);
		sessioninfo.setUserid(userid);
		sessioninfo.setSessionInfo(listSession);
		logger.info("*****************getSessionInfo Method Done***************************");
		return sessioninfo;

	}

	/*
	 * Method to get the params Details to fork the C++ Application
	 * */
	public DownloadFileResponseDTO getParamsInfo(String recordid
			,String sessionid,Long startTime,Long endTime)
	{
		logger.info("*****************getParamsInfo Method Stated***************************");
		String starttimestamp=null;
		String endtimestamp=null;
		Optional<Session> opssession=sessionrepo.findById(sessionid);
		Session session=opssession.get();
		Optional<Records> opsrecord=recordrepo.findById(recordid);
		Records record=opsrecord.get();
		Optional<Sources> opssource=sourcerepo.findById(record.getSourceId());
		Sources source=opssource.get();
		String placerootpath=session.getPlaceRootPath();
		String taskid=UUID.randomUUID().toString();
		String downloadpath= userPath+"/"+taskid;
		if(startTime!=null && startTime!=0)
		{
			 starttimestamp=Long.toString(startTime);
		}
		else
		{
			starttimestamp=Long.toString(record.getStartTimeStamp());
		}
		if(endTime!=null && endTime!=0)
		{
			 endtimestamp=Long.toString(endTime);
		}
		else
		{
			endtimestamp=Long.toString(record.getEndTimeStamp());
		}
		String filename=downloadpath+"/"+taskid+".ts";
		DownloadFileResponseDTO downloadFileResponseDTO=new DownloadFileResponseDTO();
		downloadFileResponseDTO.setCmdPath(commandPath);
		downloadFileResponseDTO.setPlacerootpath(placerootpath);
		downloadFileResponseDTO.setUsername(session.getProducedBy());
		downloadFileResponseDTO.setSessionid(sessionid);
		downloadFileResponseDTO.setSourcename(source.getName());
		downloadFileResponseDTO.setDownloadpath(downloadpath);
		downloadFileResponseDTO.setFilename(filename);
		downloadFileResponseDTO.setStarttimestamp(starttimestamp);
		downloadFileResponseDTO.setEndtimestamp(endtimestamp);
		downloadFileResponseDTO.setRedisip(redisip);
		downloadFileResponseDTO.setPort(port);
		downloadFileResponseDTO.setPassword(password);
		downloadFileResponseDTO.setTaskid(taskid);
		logger.info("*****************getParamsInfo Method Done***************************");
		return downloadFileResponseDTO;
	}

	public boolean isvalidRequest(DownloadFileRequestDTO req)
	{
		Session session=sessionrepo.findBySessionidAndUserid(req.getSessionid(),req.getUserid());
		Records record=recordrepo.findBySessionidAndRecordid(req.getSessionid(), req.getRecordid());
		Sources source=sourcerepo.findById(record.getSourceId()).get();
		if((session!=null) &&(record!=null) && session.getStatus()!=Constants.API.Delete && source.getStatus()!=Constants.API.Delete)
		{
			return true;
		}
		return false;
	}
}
