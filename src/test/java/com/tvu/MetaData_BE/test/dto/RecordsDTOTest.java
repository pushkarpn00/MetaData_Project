package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RecordsDTO;

@RunWith(MockitoJUnitRunner.class)
public class RecordsDTOTest {

	@InjectMocks
	RecordsDTO recordsDTO;
	
	@Test
	public void testRecordsDTO() {
		recordsDTO.setEndTimeStamp(1L);
		recordsDTO.setID("test");
		recordsDTO.setStartTimeStamp(1L);
		List<String> tags = new ArrayList<>();
		recordsDTO.setTags(tags);
		recordsDTO.setTitle("test");
		
		assertEquals(1L, recordsDTO.getEndTimeStamp());
		assertEquals("test", recordsDTO.getID());
		assertEquals(tags, recordsDTO.getTags());
		assertEquals("test", recordsDTO.getTitle());
		assertEquals(1L, recordsDTO.getStartTimeStamp());
	}
}
