package com.tvu.MetaData_BE.test.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.dto.SceneDTO;


@RunWith(MockitoJUnitRunner.class)
public class ShortStoriesDTOTest {
	
	@InjectMocks
	com.tvu.Metadata_BE.dto.ShortStoriesDTO shortStoriesDTO;
	
	@Test
	public void testShortStoriesDTO() {
		shortStoriesDTO.setAurthor("test");
		shortStoriesDTO.setCreatedAt(1L);
		shortStoriesDTO.setFrontCover("test");
		shortStoriesDTO.setID("test");
		List<SceneDTO> list = new ArrayList<>();
		shortStoriesDTO.setScenes(list);
		List<String> tagsList = new ArrayList<>();
		shortStoriesDTO.setTags(tagsList);
		shortStoriesDTO.setTitle("test");
		assertEquals("test", shortStoriesDTO.getAurthor());
		assertEquals(1L, shortStoriesDTO.getCreatedAt());
		assertEquals("test", shortStoriesDTO.getFrontCover());
		assertEquals("test", shortStoriesDTO.getID());
		assertEquals(list, shortStoriesDTO.getScenes());
		assertEquals(tagsList, shortStoriesDTO.getTags());
		assertEquals("test", shortStoriesDTO.getTitle());


		
	}
}
