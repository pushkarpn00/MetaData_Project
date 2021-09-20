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

import com.tvu.Metadata_BE.Model.Sources;

public interface SourceRepository extends JpaRepository<Sources, String> {
	
	List<Sources> findBySessionId(String sessionid);
	
/*	@Query(value="SELECT s.* from sources s where s.sessionid=:sessionid AND s.endtime>0 ORDER BY s.endtime DESC", nativeQuery = true)
	public List<Sources> findBySessionidAndEndTime( @Param("sessionid") String sessionid);*/
	
	@Query(value="SELECT s.* from sources s where (s.sessionid=:sessionid) AND (s.status=0 OR s.status=1) ORDER BY s.endtime DESC", nativeQuery = true)
	public List<Sources> findBySessionidAndEndTime( @Param("sessionid") String sessionid);
	
	
	@Query(value="SELECT s.* from sources s where s.id=:sourceid AND s.sessionid=:sessionid", nativeQuery = true)
	public Sources findBySessionidAndSourceid( @Param("sessionid") String sessionid,@Param("sourceid") String sourceid);

}
