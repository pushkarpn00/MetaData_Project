package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.MarkerInfo;
import com.tvu.Metadata_BE.dto.MarkerResponse;

@RunWith(MockitoJUnitRunner.class)
public class MarkerResponseTest {

	@InjectMocks
	MarkerResponse markerResponse;
	
	@Test
	public void testMarkerResponse() {
		markerResponse.setErrorCode("test");
		markerResponse.setErrorMessage("test");
		List<MarkerInfo> list = new ArrayList<MarkerInfo>();
		markerResponse.setMarkerinfo(list);

		assertEquals("test", markerResponse.getErrorCode());
		assertEquals("test", markerResponse.getErrorMessage());
		assertEquals(list, markerResponse.getMarkerinfo());


	}
	
}
