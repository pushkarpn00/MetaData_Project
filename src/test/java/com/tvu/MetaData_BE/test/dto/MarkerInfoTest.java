package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.MarkerInfo;

@RunWith(MockitoJUnitRunner.class)
public class MarkerInfoTest {

	@InjectMocks
	MarkerInfo markerInfo;
	

	@Test
	public void testMarkerInfo() {
		markerInfo.setEndtimestamp(1L);
		markerInfo.setId("test");
		markerInfo.setShmName("test");
		markerInfo.setSize("test");
		markerInfo.setStarttimestamp(1L);
		markerInfo.setTitle("test");
		
		assertEquals(1L, markerInfo.getEndtimestamp());
		assertEquals("test", markerInfo.getId());
		assertEquals("test", markerInfo.getShmName());
		assertEquals("test", markerInfo.getSize());
		assertEquals(1L, markerInfo.getStarttimestamp());
		assertEquals("test", markerInfo.getTitle());
	}
}
