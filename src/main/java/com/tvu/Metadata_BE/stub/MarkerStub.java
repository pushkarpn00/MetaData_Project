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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.Marker;
import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.dto.MarkerDTO;
import com.tvu.Metadata_BE.dto.MarkerInfo;
import com.tvu.Metadata_BE.dto.MarkerResponse;
import com.tvu.Metadata_BE.dto.ResponseDTO;
import com.tvu.Metadata_BE.repository.MarkerRepository;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.SessionRepository;
import com.tvu.Metadata_BE.util.CommonUtility;


@Service
public class MarkerStub {

	@Autowired
	private MarkerRepository markerepo;
	@Autowired
	private RecordRepository recordrepo;
	@Autowired
	private SessionRepository sessionrepo;


	public ResponseDTO saveMarker(MarkerDTO markerDTO)
	{
		ResponseDTO responseDTO=new ResponseDTO();
		String recordid=null;
		try{
			/*if(markerDTO.getEndtimestamp()> 0 && markerDTO.getEndtimestamp() < markerDTO.getStarttimestamp())
			{
				responseDTO.setErrorCode(Constants.API.ERROR_CODE);
				responseDTO.setErrorMessage(Constants.API.FAIL_MESSAGE);
				return responseDTO ;   
			}		*/
			if(markerDTO.getStarttimestamp()>0)
			{
				Marker marker = new Marker();
				marker.setId(markerDTO.getId());
				marker.setTitle(markerDTO.getTitle());
				marker.setSourceSharedMemoryName(markerDTO.getSourcesharedmemoryname());
				if(sessionrepo.findByProductionid(Long.parseLong(markerDTO.getProductionid()))!=null)
				{
					marker.setSessionId(sessionrepo.findByProductionid(Long.parseLong(markerDTO.getProductionid())).getId());
					marker.setUserId(sessionrepo.findByProductionid(Long.parseLong(markerDTO.getProductionid())).getProducedBy());
					recordid=sessionrepo.findByProductionid(Long.parseLong(markerDTO.getProductionid())).getId().concat("_"+markerDTO.getSourcesharedmemoryname()+"_0");	
				}
				else{
					Session session=sessionrepo.findByUsingTitleLikeProductionid(Long.parseLong(markerDTO.getProductionid()));
					if(session!=null)
					{
						marker.setSessionId(session.getId());
						marker.setUserId(session.getProducedBy());
						recordid=session.getId().concat("_"+markerDTO.getSourcesharedmemoryname()+"_0");
					}
					else{
						responseDTO.setErrorCode(Constants.API.ERROR_CODE);
						responseDTO.setErrorMessage(Constants.API.FAIL_MESSAGE);
					}
				}
				Records record=recordrepo.findById(recordid).get();
				if(record.getStartTimeStamp()>markerDTO.getStarttimestamp())      
				{
					marker.setStartTimeStamp(record.getStartTimeStamp());	
				}
				marker.setStartTimeStamp(markerDTO.getStarttimestamp());
				marker.setEndTimeStamp(markerDTO.getEndtimestamp());
				if(recordrepo.findById(recordid).get()!= null)
				{
					marker.setRecordId(recordid);
					marker.setSourceId(recordrepo.findById(recordid).get().getSourceId());
				}
				try{
					markerepo.save(marker);
				}catch (Exception e) {
					// TODO: handle exception
					responseDTO.setErrorCode(Constants.API.ERROR_CODE);
					responseDTO.setErrorMessage(e.getMessage().toString());
				}
				responseDTO.setErrorCode(Constants.API.SUCCESS_CODE);
				responseDTO.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
			}
			else
			{
				responseDTO.setErrorCode(Constants.API.ERROR_CODE);
				responseDTO.setErrorMessage(Constants.API.FAIL_MESSAGE);
			}

		}catch(Exception e)
		{
			responseDTO.setErrorCode(Constants.API.ERROR_CODE);
			responseDTO.setErrorMessage(e.getMessage().toString());
		}
		return responseDTO;}

	public MarkerResponse getMarkerbySourceid(String sourceid)
	{
		MarkerResponse response=new MarkerResponse();
		try{
			List<Marker> markerList=markerepo.findBySourceId(sourceid);
			if(!markerList.isEmpty())
			{
				List<MarkerInfo> markerInfos=new ArrayList<>();
				for(Marker marker:markerList)
				{
					MarkerInfo markerInfo=new MarkerInfo();
					markerInfo.setId(marker.getId());
					markerInfo.setShmName(marker.getSourceSharedMemoryName());
					markerInfo.setTitle(marker.getTitle());
					markerInfo.setStarttimestamp(marker.getStartTimeStamp());
					markerInfo.setEndtimestamp(marker.getEndTimeStamp());
					double timediff=marker.getEndTimeStamp()-marker.getStartTimeStamp();
					markerInfo.setSize(CommonUtility.calculatedTime(timediff));
					markerInfos.add(markerInfo);
				}
				response.setErrorCode(Constants.API.SUCCESS_CODE);
				response.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
				response.setMarkerinfo(markerInfos);
			}
			else
			{
				response.setErrorCode(Constants.API.ERROR_CODE);
				response.setErrorMessage(Constants.API.FAIL_MESSAGE);
			}
		}catch (Exception e) {
			// TODO: handle exception
			response.setErrorCode(Constants.API.ERROR_CODE);
			response.setErrorMessage(e.getMessage().toString());
		}
		return response;	
	}
	public MarkerResponse getMarkerbyProductionid(String pdId)
	{
		MarkerResponse response=new MarkerResponse();
		try{
			Session session=sessionrepo.findByProductionid(Long.parseLong(pdId));
			if(session==null)
			{
				session=sessionrepo.findByUsingTitleLikeProductionid(Long.parseLong(pdId));
			}
			if(session!=null){
				List<Marker> markerList=markerepo.findBySessionid(session.getId());
				List<MarkerInfo> markerInfos=new ArrayList<>();
				for(Marker marker:markerList)
				{
					MarkerInfo markerInfo=new MarkerInfo();
					markerInfo.setId(marker.getId());
					markerInfo.setShmName(marker.getSourceSharedMemoryName());
					markerInfo.setTitle(marker.getTitle());
					markerInfo.setStarttimestamp(marker.getStartTimeStamp());
					markerInfo.setEndtimestamp(marker.getEndTimeStamp());
					double timediff=marker.getEndTimeStamp()-marker.getStartTimeStamp();
					markerInfo.setSize(CommonUtility.calculatedTime(timediff));
					markerInfos.add(markerInfo);
				}
				response.setErrorCode(Constants.API.SUCCESS_CODE);
				response.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
				response.setMarkerinfo(markerInfos);
			}else{
				response.setErrorCode(Constants.API.ERROR_CODE);
				response.setErrorMessage(Constants.API.FAIL_MESSAGE);
			}
		}catch(Exception e)
		{
			response.setErrorCode(Constants.API.ERROR_CODE);
			response.setErrorMessage(e.getMessage().toString());
		}
		return response;	
	}

	public ResponseDTO deleteMarker(String markerid)
	{
		ResponseDTO responseDTO=new ResponseDTO();
		try{
			if(markerepo.findById(markerid).get()!=null){
				markerepo.deleteById(markerid);
				responseDTO.setErrorCode(Constants.API.ERROR_CODE);
				responseDTO.setErrorMessage(Constants.API.FAIL_MESSAGE);
			}else{
				responseDTO.setErrorCode(Constants.API.ERROR_CODE);
				responseDTO.setErrorMessage(Constants.API.FAIL_MESSAGE);
			}
		}catch (Exception e) {
			// TODO: handle exception
			responseDTO.setErrorCode(Constants.API.ERROR_CODE);
			responseDTO.setErrorMessage(e.getMessage().toString());
		}
		return responseDTO;
	}
	
	public List<Marker> paginationMarker(int pageNo, int pageSize) {
		try{
			Pageable paging = PageRequest.of(pageNo, pageSize);
			Page<Marker> pagedResult = markerepo.findAll(paging);
			if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        }
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		
		return new ArrayList<Marker>();
		
	}

}
