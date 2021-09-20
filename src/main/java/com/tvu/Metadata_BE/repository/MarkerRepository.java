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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tvu.Metadata_BE.Model.Marker;

public interface MarkerRepository extends JpaRepository<Marker, String> {
	
	@Query(value="SELECT m.* from marker m where m.sourceid=:sourceid ORDER BY m.endtimestamp DESC", nativeQuery = true)
	public List<Marker> findBySourceId( @Param("sourceid") String sourceid);
	@Query(value="SELECT m.* from marker m where m.sessionid=:sessionid ORDER BY m.endtimestamp DESC", nativeQuery = true)
	public List<Marker> findBySessionid( @Param("sessionid") String sessionid);
	//@Query(value="SELECT m.* from marker m where m.sourceid=:sourceid ORDER BY m.endtimestamp DESC", nativeQuery = true)
	public Page<Marker> findAll(Pageable pageable);

}
