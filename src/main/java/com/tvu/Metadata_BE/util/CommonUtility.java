package com.tvu.Metadata_BE.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.DownloadTask;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.Model.Sources;
import com.tvu.Metadata_BE.repository.DownloadTaskRepository;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.SessionRepository;
import com.tvu.Metadata_BE.repository.SourceRepository;


@Component
public class CommonUtility {

	@Value("${ACCESS_KEY_ID}")
	private String accessKey;
	@Value("${SECRET_ACCESS_KEY}")
	private String secretKey;
	@Value("${DEFAULT_REGION}")
	private String defaultRegion;
	@Value("${bucket.region.map}")
	private String bucketmap;
	@Value("${user.path}")
	private String fileBasePath;

	@Autowired
	SessionRepository sessionrepo;
	@Autowired
	DownloadTaskRepository downloadRepo;
	@Autowired
	SourceRepository sourceRepo;
	@Autowired
	RecordRepository recordRepository;

	String region=null;
	static Logger logger = Logger.getLogger(CommonUtility.class);
	public static String calculatedTime(double size)
	{
		String calculatedSize = null;
		double[] recordArray=new double[10];
		recordArray[0]=(double)size/(double)60000;
		recordArray[1]=recordArray[0]*12;
		DecimalFormat RecordnumberFormat = new DecimalFormat("0.00");
		if(recordArray[1]>0)
		{
			if(recordArray[1]<1024)
			{
				calculatedSize=(RecordnumberFormat.format(recordArray[1])+" MB");
			}
			else if(recordArray[1]>=1024)
			{
				double Recvalue=recordArray[1]/1024;
				if(Recvalue<1024) {
					calculatedSize=(RecordnumberFormat.format(Recvalue)+" GB");
				}
				else
				{
					Recvalue = Recvalue /1024;
					calculatedSize=(RecordnumberFormat.format(Recvalue)+" TB");
				}
			}

		}else {
			calculatedSize=("0 MB");
		}
		return calculatedSize;
	}
	public void deleteS3Object(Session sessionToDelete,String prefix,Sources source)
	{
		logger.info("*****************deleteS3Object Method stated*****************");
		try {
			ArrayList<KeyVersion> keys = new ArrayList<KeyVersion>();
			String bucketName=sessionToDelete.getPlaceRootPath().split("/")[0];
			region=bucketmap;
			region = region.substring(1, region.length()-1);
			String[] keyValuePairs = region.split(",");
			Map<String,String> map = new HashMap<>();
			for(String pair : keyValuePairs)
			{
				String[] entry = pair.split(":");
				map.put(entry[0].trim(), entry[1].trim());
			}
			region=map.get(bucketName);
			if(region==null)
			{
				region=defaultRegion;
			}
			String secretKeyUse=(String)new JSONObject(new String(Base64.getUrlDecoder()
					.decode(secretKey
							.substring(secretKey
									.indexOf(".")+1,secretKey
									.lastIndexOf(".")))))
					.get("appkey");
			logger.info("accessKey : :"+accessKey+"secrateAccessKey : :"+secretKeyUse+"bucketName : :"+bucketName+"region : :"+region+"Prefix  keys: :"+prefix);
			BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKeyUse);
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
					.withRegion(region)
					.build();
			if (s3Client.doesBucketExist(bucketName)) {
				ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
						.withBucketName(bucketName)
						.withPrefix(prefix);
				ObjectListing objectListing;
				do {
						objectListing = s3Client.listObjects(listObjectsRequest);
						for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
							keys.add(new KeyVersion(objectSummary.getKey()));
						}
						logger.info(keys.size() + " objects successfully added to delete.");
						DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(bucketName)
								.withKeys(keys)
								.withQuiet(true);
						s3Client.deleteObjects(multiObjectDeleteRequest);
	          listObjectsRequest.setMarker(objectListing.getNextMarker());
						keys.clear();
				} while(objectListing.isTruncated());
				System.out.println("Deletion of All keys done");
				logger.info("Deletion of keys done");
				logger.info("Deletion of keys done now removing entry from database for id : :"+sessionToDelete.getId());
			}
			if(source==null)
			{
				logger.info("DownloadTask table session status changing to deleted...");
				List<DownloadTask> downloadTaskList=downloadRepo.getBySessionid(sessionToDelete.getId());
				for(DownloadTask downloadtask:downloadTaskList)
				{
					downloadtask.setStatus(Constants.API.Delete);
					File filepath=new File(fileBasePath+"/"+downloadtask.getTaskid()+"/");
					FileUtils.deleteDirectory(filepath);
				}
			}
			else if(source!=null)
			{
				logger.info("DownloadTask table status changing to deleted...");
				downloadRepo.getBySourceid(source.getId()).forEach(downloadtask->downloadtask.setStatus(Constants.API.Delete));
			}
			logger.info("Deletion process completed...");
		}catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception occured in s3 object deltion so status changing..");
			if(source==null)
			{
				sessionToDelete.setStatus(Constants.API.Merge);
				sessionrepo.save(sessionToDelete);
				sourceRepo.findBySessionId(sessionToDelete.getId()).forEach(source1->source1.setStatus(Constants.API.Merge));
				/*recordRepository.findAllBySession(sessionToDelete.getId()).forEach(record->record.set);*/
				logger.error("Session restored and error was : : "+e.getMessage());
			}else if(source!=null)
			{
				source.setStatus(Constants.API.Merge);
				sourceRepo.save(source);
				logger.error("Source restored and error was : : "+e.getMessage());
			}
		}
		logger.info("*****************AsyncBackGroundWithTaskid Method done*****************");
	}
}
