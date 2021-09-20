/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tvu.Metadata_BE.Model.DownloadTask;

public interface DownloadTaskRepository extends JpaRepository<DownloadTask, Long>{

	DownloadTask findByTaskid(String taskid);
	
	@Query(value="SELECT d.* from downloadtask d where d.recordid=:recordid AND d.sessionid=:sessionid", nativeQuery = true)
	public List<DownloadTask> getDownloadByRecordAndSession( @Param("recordid") String recordid, @Param("sessionid") String sessionid);

	@Query(value="SELECT d.* from downloadtask d where d.recordid=:recordid AND d.sessionid=:sessionid AND d.starttimestamp=:starttime AND d.endtimestamp=:endtime AND ((d.status=1) OR (d.status=2))", nativeQuery = true)
	public DownloadTask getBySessionRecordStartAndEndTime( @Param("recordid") String recordid, @Param("sessionid") String sessionid,@Param("starttime") Long starttime,@Param("endtime") Long endtime);

	@Query(value="SELECT d.* from downloadtask d where d.sessionid=:sessionid AND d.status=2", nativeQuery = true)
	public List<DownloadTask> getBySessionid( @Param("sessionid") String sessionid);
	
	@Query(value="SELECT d.* from downloadtask d where d.sourceid=:sourceid AND d.status=2", nativeQuery = true)
	public List<DownloadTask> getBySourceid( @Param("sourceid") String sourceid);
	
	@Query(value="SELECT d.* from downloadtask d where ((d.sourceid=:sourceid) OR (d.sessionid=:sessionid)) AND d.status=1", nativeQuery = true)
	public List<DownloadTask> findBySourceidOrSessionid( @Param("sourceid") String sourceid,@Param("sessionid") String sessionid);
}

