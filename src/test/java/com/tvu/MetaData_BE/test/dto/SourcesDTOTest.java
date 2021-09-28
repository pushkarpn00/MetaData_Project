package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RecordsDTO;
import com.tvu.Metadata_BE.dto.SourceDTO;
import com.tvu.Metadata_BE.dto.SourcesDTO;

@RunWith(MockitoJUnitRunner.class)
public class SourcesDTOTest {

	@InjectMocks
	SourcesDTO sourcesDTO;
	
	@Mock
	RecordsDTO recordsDTO;
	
	@Test
	public void testTaskDTO() {
		
		sourcesDTO.setID("task");
		sourcesDTO.setName("task");
		sourcesDTO.setStatus(1234);
		sourcesDTO.setStartTimeStamp(1234l);
		sourcesDTO.setEndTimeStamp(1234l);
		
		List<RecordsDTO> list = new ArrayList<>();
		list.add(recordsDTO);
		sourcesDTO.setRecords(list);
		
		assertEquals("task", sourcesDTO.getID());
		assertEquals("task", sourcesDTO.getName());
		assertEquals(1234, sourcesDTO.getStatus());
		assertEquals(1234l, sourcesDTO.getStartTimeStamp());
		assertEquals(1234l, sourcesDTO.getEndTimeStamp());
		assertEquals(list, sourcesDTO.getRecords());
	}
}
