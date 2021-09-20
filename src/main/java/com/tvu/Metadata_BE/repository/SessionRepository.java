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

import com.tvu.Metadata_BE.Model.Session;

public interface SessionRepository extends JpaRepository<Session, String> {

	
	public Session findByProductionid(Long productionid);
	
	@Query(value="SELECT s.* from session s where s.producedby=:producedBy AND (s.status=0 OR s.status=1)", nativeQuery = true)
	List<Session> findUsingProducedBy(String producedBy);

	@Query(value="SELECT s.* from session s where ((s.producedby=:userid) AND (s.status=0 OR s.status=1) AND (s.title LIKE %:searchparam% OR s.description LIKE %:searchparam%)) ORDER BY s.starttime DESC limit :offset,:limitval", nativeQuery = true)
	public List<Session> getSessionByProducedByWithSearch( @Param("userid") String userid,@Param("offset") int offset, @Param("limitval") int limitval,@Param("searchparam") String searchparam);

	@Query(value="SELECT s.* from session s where s.producedby=:userid AND (s.status=0 OR s.status=1) ORDER BY s.starttime DESC limit :offset,:limitval", nativeQuery = true)
	public List<Session> getSessionByProducedBy( @Param("userid") String userid,@Param("offset") int offset, @Param("limitval") int limitval);

	@Query(value="SELECT s.* from session s where s.id=:sessionid AND s.producedby=:userid", nativeQuery = true)
	public Session findBySessionidAndUserid( @Param("sessionid") String sessionid,@Param("userid") String userid);

	@Query(value="SELECT s.* from session s where (s.producedby=:userid) AND (s.status=0 OR s.status=1) AND (s.title LIKE %:searchparam% OR s.description LIKE %:searchparam%)", nativeQuery = true)
	public List<Session> getcount( @Param("userid") String userid,@Param("searchparam") String searchparam);

	@Query(value="SELECT s.* from session s where s.title like %:productionid%", nativeQuery = true)
	Session findByUsingTitleLikeProductionid(Long productionid);
}
