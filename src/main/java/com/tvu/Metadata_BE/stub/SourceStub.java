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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.Sources;
import com.tvu.Metadata_BE.dto.SourceDTO;
import com.tvu.Metadata_BE.dto.SourcesResponse;
import com.tvu.Metadata_BE.repository.SourceRepository;
import com.tvu.Metadata_BE.util.CommonUtility;


@Service("sourceStub")
public class SourceStub {

	@Autowired
	private SourceRepository sourceRepository;

	static Logger logger = Logger.getLogger(SourceStub.class);

	/*
	 * Method to get sources the Details
	 * */
	public SourcesResponse getListSourceInfo(String sessionid)
	{
		logger.info("*****************getListSourceInfo Method Stated***************************");
		SourcesResponse sourceinfo=new SourcesResponse();
		sourceinfo.setSessionid(sessionid);
		List<Sources> listsource=sourceRepository.findBySessionidAndEndTime(sessionid);
		List<SourceDTO> sourceInfoDTOList=new ArrayList<SourceDTO>();
		for(Sources source:listsource)
		{
			SourceDTO sourceInfo=new SourceDTO();
			sourceInfo.setID(source.getId());
			sourceInfo.setName(source.getName());
			sourceInfo.setStatus(source.getStatus());
			sourceInfo.setStartTimeStamp(source.getStartTime());
			sourceInfo.setEndTimeStamp(source.getStopTime());
			double value1=(source.getStopTime()-source.getStartTime());
			System.out.println(source.getStartTime()+"/"+source.getStopTime());
			sourceInfo.setSize(CommonUtility.calculatedTime(value1));
			sourceInfoDTOList.add(sourceInfo);
		}
		sourceinfo.setErrorCode(Constants.API.SUCCESS_CODE);
		sourceinfo.setErrorMessage(Constants.API.SUCCESS_MESSAGE_GET);
		sourceinfo.setSourcesInfo(sourceInfoDTOList);
		logger.info("*****************getListSourceInfo Method Done***************************");
		return sourceinfo;
	}
}
