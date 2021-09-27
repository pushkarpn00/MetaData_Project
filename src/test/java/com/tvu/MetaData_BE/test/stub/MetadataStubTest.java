package com.tvu.MetaData_BE.test.stub;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.Model.ShortStories;
import com.tvu.Metadata_BE.controller.MetaDataController;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.ShortStoriesRepository;
import com.tvu.Metadata_BE.stub.MarkerStub;

@RunWith(MockitoJUnitRunner.class)
public class MetadataStubTest {

	@InjectMocks
	private MetaDataController controllerUT = new MetaDataController();

	@InjectMocks
	private MarkerStub markerStudbUT;

	@Mock
	private ShortStoriesRepository storyrepo;

	@Mock
	private RecordRepository recordrepo;
	
	@Test
	public void saveDataTest() throws Exception {
		
		Records rec=new Records();
		rec.setId("TestId1");
		Session session = new Session();
		session.setDescription("Des1");
		session.setId("TestId");
		session.setProductionid(1L);
		session.setPlaceRootPath("RootPath");
		session.setProducedBy("Test Prducer");
		session.setProgramid(3433L);
		session.setProgramname("Test Programme");
		session.setEndTime(3463463L);
		session.setTitle("Test Title");
		session.setStatus(200);
		session.setStartTime(34343L);
		rec.setSession(session);

		rec.setSourceId("TestSourceID");
		rec.setTags("TestTag");
		rec.setTitle("TestTitle");
		rec.setStartTimeStamp(123445L);
		rec.setEndTimeStamp(143454L);
		
		when(recordrepo.save(Mockito.any(Records.class))).thenReturn(rec);
		
		Records created = recordrepo.save(rec);
		assertThat(created.getId()).isSameAs(rec.getId());
		
		ShortStories stories=new ShortStories();
		stories.setId("TestId");
		stories.setAurthor("TestAuther");
		session.setDescription("Des1");
		session.setId("TestId");
		session.setProductionid(1L);
		session.setPlaceRootPath("RootPath");
		session.setProducedBy("Test Prducer");
		session.setProgramid(3433L);
		session.setProgramname("Test Programme");
		session.setEndTime(3463463L);
		session.setTitle("Test Title");
		session.setStatus(200);
		session.setStartTime(34343L);
		stories.setSession(session);
		stories.setFrontCover("TestFrontCover");
		
		stories.setTag("TestTags");
		stories.setTitle("TestTitle");
		stories.setCreatedAt(234567L);
		when(storyrepo.save(Mockito.any(ShortStories.class))).thenReturn(stories);
		
		ShortStories shortStories = storyrepo.save(stories);
		assertThat(shortStories.getId()).isSameAs(stories.getId());
		assertThat(shortStories.getAurthor()).isSameAs(stories.getAurthor());
		assertThat(shortStories.getScenes()).isSameAs(stories.getScenes());

		assertThat(shortStories.getCreatedAt()).isSameAs(stories.getCreatedAt());
		assertThat(shortStories.getFrontCover()).isSameAs(stories.getFrontCover());
		assertThat(shortStories.getSession()).isSameAs(stories.getSession());
		assertThat(shortStories.getTag()).isSameAs(stories.getTag());
		assertThat(shortStories.getTitle()).isSameAs(stories.getTitle());
		

	}	
	
	
	
	 
}
