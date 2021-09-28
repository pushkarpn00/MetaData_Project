package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RecordInfoDTO;
import com.tvu.Metadata_BE.dto.RecordInfoRecordDTO;

@RunWith(MockitoJUnitRunner.class)
public class RecordInfoDTOTest {

	@InjectMocks
	RecordInfoDTO recordInfoDTO;
	
	@Test
	public void testRecordInfoDTO() {
		recordInfoDTO.setEndTimeStamp(1L);
		recordInfoDTO.setID("test");
		recordInfoDTO.setName("test");
		List<RecordInfoRecordDTO> list = new ArrayList<>();
		recordInfoDTO.setRecords(list);
		recordInfoDTO.setSize("test");
		recordInfoDTO.setStartTimeStamp(1L);
		recordInfoDTO.setStatus(1);
		
		assertEquals(1L, recordInfoDTO.getEndTimeStamp());
		assertEquals("test", recordInfoDTO.getID());
		assertEquals("test", recordInfoDTO.getName());
		assertEquals(list, recordInfoDTO.getRecords());
		assertEquals("test", recordInfoDTO.getSize());
		assertEquals(1L, recordInfoDTO.getStartTimeStamp());
		assertEquals(1, recordInfoDTO.getStatus());






		
		
	}
}
