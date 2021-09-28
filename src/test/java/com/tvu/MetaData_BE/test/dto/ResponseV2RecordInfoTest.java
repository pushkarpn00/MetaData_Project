package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import com.tvu.Metadata_BE.dto.RecordInfoRecordDTO;
import com.tvu.Metadata_BE.dto.ResponseV2RecordInfo;

@RunWith(MockitoJUnitRunner.class)
public class ResponseV2RecordInfoTest {

	@InjectMocks
	ResponseV2RecordInfo responseV2RecordInfo;
	
	@Test
	public void testRequestSessionInfo() {
		responseV2RecordInfo.setErrorCode("test");
		responseV2RecordInfo.setErrorMessage("test");
		List<RecordInfoRecordDTO> recordInfo = new ArrayList<>();
		responseV2RecordInfo.setRecordinfo(recordInfo);
		responseV2RecordInfo.setSessionid("test");
		responseV2RecordInfo.setSourceid("test");
		assertEquals("test", responseV2RecordInfo.getErrorCode());
		assertEquals("test", responseV2RecordInfo.getErrorMessage());
		assertEquals(recordInfo, responseV2RecordInfo.getRecordinfo());
		assertEquals("test", responseV2RecordInfo.getSessionid());
		assertEquals("test", responseV2RecordInfo.getSourceid());

		
	}
}
