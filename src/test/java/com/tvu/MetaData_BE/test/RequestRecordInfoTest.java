package com.tvu.MetaData_BE.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RequestRecordInfo;

@RunWith(MockitoJUnitRunner.class)
public class RequestRecordInfoTest {

	@InjectMocks
	RequestRecordInfo requestRecordInfo;
	
	@Test
	public void testRequestRecordInfo() {
		requestRecordInfo.setSessionid("1234");
		requestRecordInfo.setUserid("xyz");
		
		assertEquals("1234", requestRecordInfo.getSessionid());
		assertEquals("xyz", requestRecordInfo.getUserid());
	}
}
