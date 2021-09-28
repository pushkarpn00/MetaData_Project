package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import com.tvu.Metadata_BE.dto.ResponseSessionInfo;
import com.tvu.Metadata_BE.dto.SessionDTO;

@RunWith(MockitoJUnitRunner.class)

public class ResponseSessionInfoTest {

	@InjectMocks
	ResponseSessionInfo responseSessionInfo;
	
	@Test
	public void testResponseSessionInfo() {
		responseSessionInfo.setCount(1);
		responseSessionInfo.setErrorCode("test");
		responseSessionInfo.setErrorMessage("test");
		List<SessionDTO> list = new ArrayList<>();
		responseSessionInfo.setSessionInfo(list);
		responseSessionInfo.setTotal_count(1);
		responseSessionInfo.setUserid("test");
		
		assertEquals("test", responseSessionInfo.getErrorCode());
		assertEquals(1, responseSessionInfo.getCount());
		assertEquals(list, responseSessionInfo.getSessionInfo());
		assertEquals(1, responseSessionInfo.getTotal_count());
		assertEquals("test", responseSessionInfo.getUserid());
		assertEquals("test", responseSessionInfo.getErrorCode());


	}
}
