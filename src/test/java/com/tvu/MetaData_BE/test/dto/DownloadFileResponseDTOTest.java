package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.DownloadFileResponseDTO;

@RunWith(MockitoJUnitRunner.class)
public class DownloadFileResponseDTOTest {

	@InjectMocks
	DownloadFileResponseDTO downloadFileResponseDTO;
	
	@Test
	public void testDownloadFileResponseDTO() {
		downloadFileResponseDTO.setCmdPath("cmd");
		downloadFileResponseDTO.setPlacerootpath("cmd");
		downloadFileResponseDTO.setUsername("cmd");
		downloadFileResponseDTO.setSessionid("cmd");
		downloadFileResponseDTO.setSourcename("cmd");
		downloadFileResponseDTO.setDownloadpath("cmd");
		downloadFileResponseDTO.setFilename("cmd");
		downloadFileResponseDTO.setStarttimestamp("cmd");
		downloadFileResponseDTO.setEndtimestamp("cmd");
		downloadFileResponseDTO.setRedisip("cmd");
		downloadFileResponseDTO.setPort("cmd");
		downloadFileResponseDTO.setPassword("cmd");
		downloadFileResponseDTO.setTaskid("cmd");
		
		assertEquals("cmd", downloadFileResponseDTO.getCmdPath());
		assertEquals("cmd", downloadFileResponseDTO.getPlacerootpath());
		assertEquals("cmd", downloadFileResponseDTO.getUsername());
		assertEquals("cmd", downloadFileResponseDTO.getSessionid());
		assertEquals("cmd", downloadFileResponseDTO.getSourcename());
		assertEquals("cmd", downloadFileResponseDTO.getDownloadpath());
		assertEquals("cmd", downloadFileResponseDTO.getFilename());
		assertEquals("cmd", downloadFileResponseDTO.getStarttimestamp());
		assertEquals("cmd", downloadFileResponseDTO.getEndtimestamp());
		assertEquals("cmd", downloadFileResponseDTO.getRedisip());
		assertEquals("cmd", downloadFileResponseDTO.getPort());
		assertEquals("cmd", downloadFileResponseDTO.getPassword());
		assertEquals("cmd", downloadFileResponseDTO.getTaskid());
	}
	
	
}
