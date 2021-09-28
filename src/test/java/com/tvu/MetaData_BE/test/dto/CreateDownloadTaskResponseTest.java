package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.CreateDownloadTaskResponse;

@RunWith(MockitoJUnitRunner.class)
public class CreateDownloadTaskResponseTest {

	@InjectMocks
	CreateDownloadTaskResponse createDownloadTaskResponse;
	
	@Test
	public void testCreateDownloadTaskResponse() {
		createDownloadTaskResponse.setErrorCode("test");
		createDownloadTaskResponse.setErrorMessage("test");
		createDownloadTaskResponse.setStatus(1);
		createDownloadTaskResponse.setTaskid("test");
		assertEquals("test", createDownloadTaskResponse.getErrorCode());
		assertEquals("test", createDownloadTaskResponse.getErrorMessage());
		assertEquals(1, createDownloadTaskResponse.getStatus());
		assertEquals("test", createDownloadTaskResponse.getTaskid());
	}
}
