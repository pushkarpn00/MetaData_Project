package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import com.tvu.Metadata_BE.dto.ResponseDTO;

@RunWith(MockitoJUnitRunner.class)
public class ResponseDTOTest {


	@InjectMocks
	ResponseDTO responseDTO;
	
	@Test
	public void testRequestSessionInfo() {
		responseDTO.setErrorCode("test");
		responseDTO.setErrorMessage("test");
		assertEquals("test", responseDTO.getErrorCode());
		assertEquals("test", responseDTO.getErrorMessage());

	}
}
