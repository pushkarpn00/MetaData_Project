package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.RequestSessionInfo;


@RunWith(MockitoJUnitRunner.class)
public class RequestSessionInfoTest {

	@InjectMocks
	RequestSessionInfo requestSessionInfo;
	
	@Test
	public void testRequestSessionInfo() {
		requestSessionInfo.setLimit(1);
		requestSessionInfo.setOffset(1);
		requestSessionInfo.setUserid("test");
		assertEquals(1, requestSessionInfo.getLimit());
		assertEquals(1, requestSessionInfo.getOffset());
		assertEquals("test", requestSessionInfo.getUserid());
	}
}
