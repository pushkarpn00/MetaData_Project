package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.SessionDTO;

@RunWith(MockitoJUnitRunner.class)
public class SessionDTOTest {
	
	@InjectMocks
	SessionDTO sessionDTO;
	
	@Test
	public void testSessionDTO() {
		sessionDTO.setDescription("test");
		sessionDTO.setEndTime(1L);
		sessionDTO.setId("test");
		sessionDTO.setPlaceRootPath("test");
		sessionDTO.setProducedBy("test");
		sessionDTO.setProductionid(1L);
		sessionDTO.setProgramid(1L);
		sessionDTO.setProgramname("test");
		sessionDTO.setSize("test");
		sessionDTO.setStartTime(1L);
		sessionDTO.setStatus(1);
		sessionDTO.setTitle("test");
		
		assertEquals("test", sessionDTO.getDescription());
		assertEquals(1L, sessionDTO.getEndTime());
		assertEquals("test", sessionDTO.getId());
		assertEquals("test", sessionDTO.getPlaceRootPath());
		assertEquals("test", sessionDTO.getProducedBy());
		assertEquals(1L, sessionDTO.getProductionid());
		assertEquals(1L, sessionDTO.getProgramid());
		assertEquals("test", sessionDTO.getProgramname());
		assertEquals("test", sessionDTO.getSize());
		assertEquals(1L, sessionDTO.getStartTime());
		assertEquals(1, sessionDTO.getStatus());
		assertEquals("test", sessionDTO.getTitle());











		
	}
	
}
