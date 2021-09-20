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

import com.tvu.Metadata_BE.Model.Records;

public interface RecordRepository extends JpaRepository<Records, String> {
	
	/*public List<Records> findAllBySession(String sessioid);*/
	
	@Query(value="SELECT r.* from records r where r.sourceid=:sourceid AND r.sessionid=:sessionid", nativeQuery = true)
	public List<Records> getAllRecordBySourceidAndSessionID( @Param("sourceid") String sourceid,@Param("sessionid") String sessionid);
	
	@Query(value="SELECT r.* from records r where r.sourceid=:sourceid AND r.sessionid=:sessionid AND r.endtime>0 ORDER BY r.endtime DESC", nativeQuery = true)
	public List<Records> getAllRecordBySourceidAndSessionIDAndEndtime( @Param("sourceid") String sourceid,@Param("sessionid") String sessionid);
	
	@Query(value="SELECT r.* from records r where r.id=:recordid AND r.sessionid=:sessionid", nativeQuery = true)
	public Records findBySessionidAndRecordid( @Param("sessionid") String sessionid,@Param("recordid") String recordid);


}
