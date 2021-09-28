package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RecordInfoRecordDTO;
import com.tvu.Metadata_BE.dto.TaskDTO;

@RunWith(MockitoJUnitRunner.class)
public class RecordInfoRecordDTOTest {

	@InjectMocks
	RecordInfoRecordDTO recordInfoRecordDTO;
	
	@Test
	public void testRecordInfoRecordDTO() {
		recordInfoRecordDTO.setEndTimeStamp(1L);
		recordInfoRecordDTO.setID("test");
		recordInfoRecordDTO.setSize("test");
		recordInfoRecordDTO.setStartTimeStamp(1L);
		List<String> list = new ArrayList<>();
		recordInfoRecordDTO.setTags(list);
		List<TaskDTO> taskDTOList = new ArrayList<>();
		recordInfoRecordDTO.setTasks(taskDTOList);
		recordInfoRecordDTO.setTitle("test");
		
		assertEquals(1L, recordInfoRecordDTO.getEndTimeStamp());
		assertEquals("test", recordInfoRecordDTO.getID());
		assertEquals("test", recordInfoRecordDTO.getSize());
		assertEquals(1L, recordInfoRecordDTO.getStartTimeStamp());
		assertEquals(list, recordInfoRecordDTO.getTags());
		assertEquals(taskDTOList, recordInfoRecordDTO.getTasks());
		assertEquals("test", recordInfoRecordDTO.getTitle());
		
	}
}
