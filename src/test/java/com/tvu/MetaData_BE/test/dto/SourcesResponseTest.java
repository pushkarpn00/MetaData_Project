package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.SourceDTO;
import com.tvu.Metadata_BE.dto.SourcesResponse;

@RunWith(MockitoJUnitRunner.class)
public class SourcesResponseTest {

	@InjectMocks
	SourcesResponse sourcesResponse;
	
	@Mock
	SourceDTO sourceDTO;
	
	@Test
	public void testTaskDTO() {
		
		sourcesResponse.setErrorCode("task");
		sourcesResponse.setErrorMessage("task");
		sourcesResponse.setSessionid("task");
		
		List<SourceDTO> list = new ArrayList<>();
		list.add(sourceDTO);
		sourcesResponse.setSourcesInfo(list);
		
		assertEquals("task", sourcesResponse.getErrorCode());
		assertEquals("task", sourcesResponse.getErrorMessage());
		assertEquals("task", sourcesResponse.getSessionid());
		assertEquals(list, sourcesResponse.getSourcesInfo());
	}
}
