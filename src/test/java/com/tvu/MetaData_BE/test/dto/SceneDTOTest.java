package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.SceneDTO;

@RunWith(MockitoJUnitRunner.class)
public class SceneDTOTest {
	
	@InjectMocks
	SceneDTO sceneDTO;
	
	@Test
	public void testSceneDTO() {
		sceneDTO.setAssociatedSource("test");
		sceneDTO.setEndTimeStamp(1L);
		sceneDTO.setStartTimeStamp(1L);
		
		assertEquals("test", sceneDTO.getAssociatedSource());
		assertEquals(1L, sceneDTO.getEndTimeStamp());
		assertEquals(1L, sceneDTO.getStartTimeStamp());
	}

}
