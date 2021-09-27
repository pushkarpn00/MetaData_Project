package com.tvu.MetaData_BE.test.controller;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.tvu.Metadata_BE.controller.SystemController;



@RunWith(MockitoJUnitRunner.class)
public class SystemControllerTest {

	@InjectMocks
	private SystemController systemController;
	
	@Before
	public void setup() {
		
		ReflectionTestUtils.setField(systemController, "buildVersion", "version1.0.0 build35");
	}
	
	@Test
	public void testBaseVersion() {
		
		assertEquals("version1.0.0 build35|", systemController.baseVersion());
	}
}