package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.Model.Scene;
import com.tvu.Metadata_BE.Model.ShortStories;

@RunWith(MockitoJUnitRunner.class)
public class SceneTest {

	@InjectMocks
	Scene scene;
	
	@Mock
	ShortStories shortStory;
	
	@Test
	public void testScene() {
		
		scene.setShortStory(shortStory);
		scene.setSourceid("test");
		scene.setStartTimeStamp(1L);
		scene.setStopTimeStamp(1L);
		
		assertEquals(shortStory, scene.getShortStory());
		assertEquals("test", scene.getSourceid());
		assertEquals(1L, scene.getStartTimeStamp());
		assertEquals(1L, scene.getStopTimeStamp());
	}
}
