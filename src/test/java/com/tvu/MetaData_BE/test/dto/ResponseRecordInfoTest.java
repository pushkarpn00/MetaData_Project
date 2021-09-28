package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RecordInfoDTO;
import com.tvu.Metadata_BE.dto.ResponseRecordInfo;

@RunWith(MockitoJUnitRunner.class)
public class ResponseRecordInfoTest {

	@InjectMocks
	ResponseRecordInfo responseRecordInfo;
	
	@Test
	public void testResponseRecordInfo() {
		responseRecordInfo.setErrorCode("test");
		responseRecordInfo.setErrorMessage("test");
		List<RecordInfoDTO> list = new ArrayList<>();
		responseRecordInfo.setRecordinfo(list);
		responseRecordInfo.setSessionid("test");;
		
		assertEquals("test", responseRecordInfo.getErrorCode());
		assertEquals("test", responseRecordInfo.getErrorMessage());
		assertEquals(list, responseRecordInfo.getRecordinfo());
		assertEquals("test", responseRecordInfo.getSessionid());
	}
}
