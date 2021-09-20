/* * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.*/
package com.tvu.Metadata_BE.scheduler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.DownloadTask;
import com.tvu.Metadata_BE.repository.DownloadTaskRepository;

@Service
public class Scheduler {

	static Logger logger = Logger.getLogger(Scheduler.class);
	//private static boolean isFinished = false;
	@Autowired
	DownloadTaskRepository downloadrepo;

	@Value("${user.path}")
	private String fileBasePath;

	@Scheduled(fixedRate = 4*60*60*1000)
	public void delete() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		logger.info("Scheduler Luanched at : : " + strDate);
		List<DownloadTask> listDownloadTask=downloadrepo.findAll();
		/*
		 * Delete unused folder from server after matching with DATABASE
		 */

		File directoryPath = new File(fileBasePath+"/");
		logger.info("directoryPath : :"+directoryPath);
		String contents[] = directoryPath.list();
		logger.info("Number of folder present in download directories on Server: : "+contents.length);

		for(int i=0; i<contents.length; i++)
		{
			DownloadTask checkforExistance=downloadrepo.findByTaskid(contents[i]);
			if(checkforExistance==null && !contents[i].contains(Constants.API.logger))
			{
				logger.info("Operation on : :"+contents[i]);
				File filepath=new File(fileBasePath+"/"+contents[i]+"/");
				FileUtils.deleteDirectory(filepath);
				logger.info("^^^^^Directory not found in db :: So,Folder Deleted : :"+filepath+"^^^^^^^^^^^^^^^^");
			}
			/*
			 * check for download failed directories
			 */
			else if(checkforExistance!=null && ((checkforExistance.getStatus().equals(Constants.API.Failed)||(checkforExistance.getStatus().equals(Constants.API.Delete)))))
			{
				logger.info("Operation on : :"+contents[i]);
				File filepath=new File(fileBasePath+"/"+contents[i]+"/");
				FileUtils.deleteDirectory(filepath);
				logger.info("^^^^^^^^^Failed taskid :: So,Folder Deleted : :"+filepath+"^^^^^^^^^^^^^^^^");

			}
		}

		/*
		 * List of all taskid need cleanup and update the status in database
		 */
		
		for(DownloadTask downloadTask:listDownloadTask)
		{
			boolean value=checkNeedCleanup(downloadTask.getMergedtime(),downloadTask.getKeepduration());
			if(value && (downloadTask.getStatus()==Constants.API.Ready))
			{
				logger.info("Merged Time : :"+downloadTask.getMergedtime()+"KeepAlive Time : :"+downloadTask.getKeepduration());
				logger.info("File "+downloadTask.getFilename()+"Merged Time : :"+downloadTask.getMergedtime()+"KeepAlive Time : :"+downloadTask.getKeepduration());
				logger.info("Check For cleanup , KeepDuration Time in hrs was "+downloadTask.getKeepduration()+"===========>"+value);
				logger.info("Deleting directory");
				Path path=Paths.get(fileBasePath+"/"+downloadTask.getTaskid()+"/");
				boolean isDirectory=Files.isDirectory(path);
				if(isDirectory){
					deleteDirectoryStream(path);
				}
				DownloadTask downloadTask1=downloadrepo.findByTaskid(downloadTask.getTaskid());
				downloadTask1.setDeletetime(System.currentTimeMillis());
				downloadTask1.setStatus(Constants.API.Delete);
				downloadrepo.save(downloadTask1);
				logger.info("Deletion Time updated for Taskid : :"+downloadTask.getTaskid());
			}
		}

	}
	public boolean checkNeedCleanup(Long mergedTime,int keepalive )
	{
		if(mergedTime!=null)
		{
			Long value1=System.currentTimeMillis()-mergedTime;
			Long value2=(long) (keepalive*60*60*1000);
			if(value1>value2)
			{
				//logger.info("Taskid has expired so move for cleanup...");
				return true;
			}
		}
		else
		{
			logger.info("Merged Time value : : null");
		}
		return false;
	}
	void deleteDirectoryStream(Path path) throws IOException {
		Files.walk(path)
		.sorted(Comparator.reverseOrder())
		.map(Path::toFile)
		.forEach(File::delete);
	}
	/*
	@Scheduled(fixedRate = 2*60*60*1000)
	public void emptyDirectoryCleanup()
	{
		do {
			isFinished = true;
			deleteEmptyDirectory(fileBasePath+"/");
		} while (!isFinished);

	}
	private static void deleteEmptyDirectory(String fileLocation) {
		File folder = new File(fileLocation);
		File[] listofFiles = folder.listFiles();
		if (listofFiles.length == 0) {
			logger.info("Folder Name :: " + folder.getAbsolutePath() + " is deleted.");
			logger.info("############Empty Directory############Folder Name :: " + folder.getAbsolutePath() + " is deleted.");
			folder.delete();
			isFinished = false;
		} else {
			for (int j = 0; j < listofFiles.length; j++) {
				File file = listofFiles[j];
				if (file.isDirectory()) {
					deleteEmptyDirectory(file.getAbsolutePath());
				}
			}
		}
	}*/
}
