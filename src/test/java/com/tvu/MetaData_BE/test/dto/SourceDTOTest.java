package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RecordsDTO;
import com.tvu.Metadata_BE.dto.SourceDTO;

@RunWith(MockitoJUnitRunner.class)
public class SourceDTOTest {

	@InjectMocks
	SourceDTO sourceDTO;
	
	@Mock
	RecordsDTO recordsDTO;
	
	@Test
	public void testTaskDTO() {
		
		sourceDTO.setID("task");
		sourceDTO.setName("task");
		sourceDTO.setStatus(1234);
		sourceDTO.setStartTimeStamp(1234l);
		sourceDTO.setEndTimeStamp(1234l);
		sourceDTO.setSize("task");
		
		
		assertEquals("task", sourceDTO.getID());
		assertEquals("task", sourceDTO.getName());
		assertEquals(1234, sourceDTO.getStatus());
		assertEquals(1234l, sourceDTO.getStartTimeStamp());
		assertEquals(1234l, sourceDTO.getEndTimeStamp());
		assertEquals("task", sourceDTO.getSize());
	}
}
