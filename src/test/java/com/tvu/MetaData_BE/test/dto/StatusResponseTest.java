package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.StatusResponse;

@RunWith(MockitoJUnitRunner.class)
public class StatusResponseTest {

	@InjectMocks
	StatusResponse statusResponse;
	
	@Test
	public void testTaskDTO() {
		
		statusResponse.setErrorCode("task");
		statusResponse.setErrorMessage("task");
		statusResponse.setTaskID("task");
		statusResponse.setStatus(1234);
		
		assertEquals("task", statusResponse.getErrorCode());
		assertEquals("task", statusResponse.getErrorMessage());
		assertEquals("task", statusResponse.getTaskID());
		assertEquals(1234, statusResponse.getStatus());
	}
}
