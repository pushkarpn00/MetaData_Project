package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.MarkerDTO;

@RunWith(MockitoJUnitRunner.class)
public class MetaRequestsDTOTest {
	@InjectMocks
	MarkerDTO markerDTO;
	
	@Test
	public void testMetaRequestsDTO() {
		markerDTO.setEndtimestamp(1L);
		markerDTO.setId("test");
		markerDTO.setProductionid("test");
		markerDTO.setSourcesharedmemoryname("test");
		markerDTO.setStarttimestamp(1L);
		markerDTO.setTitle("test");
		
		assertEquals(1L, markerDTO.getEndtimestamp());
		assertEquals("test", markerDTO.getId());
		assertEquals("test", markerDTO.getProductionid());
		assertEquals("test", markerDTO.getSourcesharedmemoryname());
		assertEquals(1L, markerDTO.getStarttimestamp());
		assertEquals("test", markerDTO.getTitle());
		


		




	}
	
}
