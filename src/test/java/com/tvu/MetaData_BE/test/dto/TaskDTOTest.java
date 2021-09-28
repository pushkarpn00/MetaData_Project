package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.TaskDTO;

@RunWith(MockitoJUnitRunner.class)
public class TaskDTOTest {

	@InjectMocks
	TaskDTO taskDTO;
	
	@Test
	public void testTaskDTO() {
		
		taskDTO.setTaskid("task");
		taskDTO.setStatus(1234);
		taskDTO.setStartTimeStamp(1234l);
		taskDTO.setEndTimeStamp(1234l);
		
		assertEquals("task", taskDTO.getTaskid());
		assertEquals(1234, taskDTO.getStatus());
		assertEquals(1234l, taskDTO.getStartTimeStamp());
		assertEquals(1234l, taskDTO.getEndTimeStamp());
	}
}
