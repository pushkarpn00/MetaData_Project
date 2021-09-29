package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.Model.Sources;

@RunWith(MockitoJUnitRunner.class)
public class SourcesTest {

	@InjectMocks
	Sources sources;
	
	@Mock
	Session session;
	
	@Test
	public void testSourcesTest() {
		
		sources.setId("test");
		sources.setSession(session);
		sources.setName("test");
		sources.setStartTime(1L);
		sources.setStopTime(1L);
		sources.setStatus(1);
		
		assertEquals("test", sources.getId());
		assertEquals(session, sources.getSession());
		assertEquals("test", sources.getName());
		assertEquals(1L, sources.getStartTime());
		assertEquals(1L, sources.getStopTime());
		assertEquals(1, sources.getStatus());
	}
}
