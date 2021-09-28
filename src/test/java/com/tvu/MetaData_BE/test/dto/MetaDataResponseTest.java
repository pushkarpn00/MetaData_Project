package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.MetaDataResponse;

@RunWith(MockitoJUnitRunner.class)

public class MetaDataResponseTest {

	@InjectMocks
	MetaDataResponse metaDataResponse;
	
	@Test
	public void testMetaDataResponse() {
		metaDataResponse.setErrorCode("test");
		metaDataResponse.setErrorMessage("test");
		assertEquals("test", metaDataResponse.getErrorCode());
		assertEquals("test", metaDataResponse.getErrorMessage());

	}
}
