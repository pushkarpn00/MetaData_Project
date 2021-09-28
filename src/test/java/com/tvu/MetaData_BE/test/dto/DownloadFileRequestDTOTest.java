package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.DownloadFileRequestDTO;

@RunWith(MockitoJUnitRunner.class)
public class DownloadFileRequestDTOTest {

	@InjectMocks
	DownloadFileRequestDTO downloadFileRequestDTO;
	
	@Test
	public void testDownloadFileRequestDTOTest() {
		downloadFileRequestDTO.setEndTime(1L);
		downloadFileRequestDTO.setKeepduration(12);
		downloadFileRequestDTO.setRecordid("123");
		downloadFileRequestDTO.setSessionid("TestSessionID");
		downloadFileRequestDTO.setStartTime(1234L);
		downloadFileRequestDTO.setUserid("TestUserId");
		
		assertEquals(1L, downloadFileRequestDTO.getEndTime());
		assertEquals(12, downloadFileRequestDTO.getKeepduration());
		assertEquals("123", downloadFileRequestDTO.getRecordid());
		assertEquals("TestSessionID", downloadFileRequestDTO.getSessionid());
		assertEquals(1234L, downloadFileRequestDTO.getStartTime());
		assertEquals("TestUserId", downloadFileRequestDTO.getUserid());
	}
}
