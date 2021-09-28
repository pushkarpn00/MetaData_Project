package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RequestRecordInfo;

@RunWith(MockitoJUnitRunner.class)
public class RequestRecordInfoTest {

	@InjectMocks
	RequestRecordInfo requestRecordInfo;
	
	@Test
	public void testRequestRecordInfo() {
		requestRecordInfo.setSessionid("test");
		requestRecordInfo.setUserid("test");
		assertEquals("test", requestRecordInfo.getSessionid());
		assertEquals("test", requestRecordInfo.getUserid());

	}
}
