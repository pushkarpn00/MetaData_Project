package com.tvu.MetaData_BE.test.stub;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.Model.Sources;
import com.tvu.Metadata_BE.dto.SourcesResponse;
import com.tvu.Metadata_BE.repository.SourceRepository;
import com.tvu.Metadata_BE.stub.SourceStub;

@RunWith(MockitoJUnitRunner.class)
public class SourceStubTest {

	@InjectMocks
	SourceStub sourceStub;
	
	@Mock
	SourceRepository sourceRepository;
	
	@Mock
	Sources sources;
	
	@Test
	public void testGetListSourceInfo() {
		
		List<Sources> listsource = new ArrayList<>();
		listsource.add(sources);
		Mockito.when(sourceRepository
				.findBySessionidAndEndTime("sessionId")).thenReturn(listsource);
		
		SourcesResponse sourceinfo = sourceStub.getListSourceInfo("sessionId");
		
		assertNotNull(sourceinfo);
	}
}
