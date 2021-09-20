/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.stub;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.DownloadTask;
import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.dto.CreateDownloadTaskResponse;
import com.tvu.Metadata_BE.dto.DownloadFileRequestDTO;
import com.tvu.Metadata_BE.dto.DownloadFileResponseDTO;
import com.tvu.Metadata_BE.repository.DownloadTaskRepository;
import com.tvu.Metadata_BE.repository.RecordRepository;

@Service
public class CreateTaskStub {
	@Autowired
	DownloadTaskRepository downloadRepo;
	@Autowired
	RecordRepository recordRepo;

        @Value("${user.path}")
	private String fileBasePath;
	@Value("${ACCESS_KEY_ID}")
	private String accessKey;
	@Value("${SECRET_ACCESS_KEY}")
	private String secretKey;
	@Value("${DEFAULT_REGION}")
	private String defaultRegion;
	@Value("${bucket.region.map}")
	private String bucketmap;
	
	String region=null;
	static Logger logger = Logger.getLogger(CreateTaskStub.class);
	public CreateDownloadTaskResponse asyncBackgroundWithTaskid(DownloadFileRequestDTO req,DownloadFileResponseDTO downloadFileResponseDTO)
	{
		logger.info("*****************AsyncBackGroundWithTaskid Method stated*****************");
		CreateDownloadTaskResponse response=new CreateDownloadTaskResponse();
		Optional<Records> record=recordRepo.findById(req.getRecordid());
		Records records=record.get();
		region=bucketmap;
		region = region.substring(1, region.length()-1);
		String[] keyValuePairs = region.split(",");
		Map<String,String> map = new HashMap<>();
		for(String pair : keyValuePairs) 
		{
		    String[] entry = pair.split(":");                    
		    map.put(entry[0].trim(), entry[1].trim());
		}
		region=map.get(downloadFileResponseDTO.getPlacerootpath().split("/")[0]);
		if(region==null)
		{
			region=defaultRegion;
		}
		new Thread(new Runnable() {
			public void run()
			{
				System.out.println("Thread Forked...");
				DownloadTask downloadTask=new DownloadTask();
				downloadTask.setTaskid(downloadFileResponseDTO.getTaskid());
				downloadTask.setUserid(req.getUserid());
				downloadTask.setSourceid(records.getSourceId());
				downloadTask.setRecordid(req.getRecordid());
				downloadTask.setSessionid(req.getSessionid());
				downloadTask.setFilename(downloadFileResponseDTO.getFilename());
				if(req.getKeepduration()!=null && req.getKeepduration()!=0)
				{
					downloadTask.setKeepduration(req.getKeepduration());
				}
				else
				{
					downloadTask.setKeepduration(4);
				}
				downloadTask.setStarttime(Long.parseLong(downloadFileResponseDTO.getStarttimestamp()));
				downloadTask.setEndtime(Long.parseLong(downloadFileResponseDTO.getEndtimestamp()));
				downloadTask.setStatus(Constants.API.Merge);
				downloadTask.setCreatetime(System.currentTimeMillis());
				downloadRepo.save(downloadTask);
				//File check = new File(downloadFileResponseDTO.getFilename());
				System.out.println("File Downloading Started...");
				logger.info("File Downloding in progres..");
				try {
					String line;
					String command =  downloadFileResponseDTO.getCmdPath() +" "+
							downloadFileResponseDTO.getPlacerootpath() + " "+
							downloadFileResponseDTO.getUsername() +" "+
							downloadFileResponseDTO.getSessionid()+ " "+
							downloadFileResponseDTO.getSourcename()+ " "+
							downloadFileResponseDTO.getDownloadpath()+ " "+
							downloadFileResponseDTO.getFilename()+ " "+
							downloadFileResponseDTO.getStarttimestamp()+ " "+
							downloadFileResponseDTO.getEndtimestamp()+ " "+
							downloadFileResponseDTO.getTaskid() +" "+
							accessKey+" "+
							(String)new JSONObject(new String(Base64.getUrlDecoder()
									.decode(secretKey
											.substring(secretKey
													.indexOf(".")+1,secretKey
													.lastIndexOf(".")))))
							.get("appkey")+" "+
							region+" "+req.getRecordid()+" "+
							downloadFileResponseDTO.getRedisip()+ " "+
							downloadFileResponseDTO.getPort()+ " "+
							downloadFileResponseDTO.getPassword();
					logger.info("Bucket and Region value is : :"+downloadFileResponseDTO.getPlacerootpath().split("/")[0]+"/"+region);
					System.out.println("Execution Command : :"+command);
					logger.info("Execution Command : :"+command);
					Runtime run  = Runtime.getRuntime();
					Process proc = run.exec(command);
					InputStreamReader inputStream=new InputStreamReader(proc.getInputStream());
					BufferedReader reader =new BufferedReader(inputStream);
					logger.info("Execution Output START");
					while ((line = reader.readLine()) != null) {
					        logger.info(line);
					}
					proc.getInputStream().close();
					inputStream.close();
					reader.close();
					logger.info("Execution Output END");
					File file = new File(downloadFileResponseDTO.getFilename());
					if(file.exists())
					{
						logger.info("Downloading Completed....");
						DownloadTask downloadTask1=downloadRepo.findByTaskid(downloadFileResponseDTO.getTaskid());
						downloadTask1.setStatus(Constants.API.Ready);
						downloadTask1.setMergedtime(System.currentTimeMillis());
						downloadRepo.save(downloadTask1);
					}
					else {
						logger.error("Downloading Failed...");
						/*Path path=Paths.get(fileBasePath+"/"+downloadTask.getTaskid()+"/");
						deleteDirectoryStream(path);*/
						DownloadTask downloadTask1=downloadRepo.findByTaskid(downloadFileResponseDTO.getTaskid());
						downloadTask1.setStatus(Constants.API.Failed);
						downloadTask1.setMergedtime(0L);
						downloadRepo.save(downloadTask1);
						File filepath=new File(fileBasePath+"/"+downloadFileResponseDTO.getTaskid()+"/");
						FileUtils.deleteDirectory(filepath);
					}
					logger.info("Thread Excution Done...");
				}catch (Exception err) {
					logger.error(err.getMessage());
				}

				//This is testing piece of code
				/*for(int i=0;i<10;i++)
				{
					System.out.println("Value is"+i);
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/
			}

		}).start();
		response.setErrorCode(Constants.API.SUCCESS_CODE);
		response.setErrorMessage(Constants.API.SUCCESS_MESSAGE);
		response.setTaskid(downloadFileResponseDTO.getTaskid());
		response.setStatus(Constants.API.Merge);
		logger.info("*****************AsyncBackGroundWithTaskid Method done*****************");
		return response;
	}
}
