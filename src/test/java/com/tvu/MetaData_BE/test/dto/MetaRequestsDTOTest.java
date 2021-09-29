package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.MetaRequestsDTO;
import com.tvu.Metadata_BE.dto.ShortStoriesDTO;
import com.tvu.Metadata_BE.dto.SourcesDTO;

@RunWith(MockitoJUnitRunner.class)
public class MetaRequestsDTOTest {
	@InjectMocks
	MetaRequestsDTO metaRequestsDTO;
	
	@Mock
	SourcesDTO sourcesDTO;
	
	@Mock
	ShortStoriesDTO shortStoriesDTO;
	
	@Test
	public void testMetaRequestsDTO() {
		
		metaRequestsDTO.setId("test");
		metaRequestsDTO.setProductionID(1L);
		metaRequestsDTO.setProgramID(1L);
		metaRequestsDTO.setProgramName("test");
		metaRequestsDTO.setTitle("test");
		metaRequestsDTO.setPlaceRootPath("test");
		metaRequestsDTO.setDescription("test");
		metaRequestsDTO.setProducedBy("test");
		metaRequestsDTO.setStatus(1);
		metaRequestsDTO.setStartTime(1L);
		metaRequestsDTO.setEndTime(1L);
		
		List<SourcesDTO> listSourcesDTO = new ArrayList<>();
		listSourcesDTO.add(sourcesDTO);
		metaRequestsDTO.setSources(listSourcesDTO);
		
		List<ShortStoriesDTO> listShortStoriesDTO = new ArrayList<>();
		listShortStoriesDTO.add(shortStoriesDTO);
		metaRequestsDTO.setShortStories(listShortStoriesDTO);
		
		assertEquals("test", metaRequestsDTO.getId());
		assertEquals(1L, metaRequestsDTO.getProductionID());
		assertEquals(1L, metaRequestsDTO.getProgramID());
		assertEquals("test", metaRequestsDTO.getProgramName());
		assertEquals("test", metaRequestsDTO.getTitle());
		assertEquals("test", metaRequestsDTO.getPlaceRootPath());
		assertEquals("test", metaRequestsDTO.getDescription());
		assertEquals("test", metaRequestsDTO.getProducedBy());
		assertEquals(1, metaRequestsDTO.getStatus());
		assertEquals(1L, metaRequestsDTO.getStartTime());
		assertEquals(1L, metaRequestsDTO.getEndTime());
		assertEquals(listSourcesDTO, metaRequestsDTO.getSources());
		assertEquals(listShortStoriesDTO, metaRequestsDTO.getShortStories());

	}
	
}
