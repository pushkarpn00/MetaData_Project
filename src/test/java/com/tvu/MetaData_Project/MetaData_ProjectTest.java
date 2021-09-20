package com.tvu.MetaData_Project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tvu.Metadata_BE.Model.Marker;
import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.Model.Scene;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.Model.ShortStories;
import com.tvu.Metadata_BE.Model.Sources;
import com.tvu.Metadata_BE.controller.MetaDataController;
import com.tvu.Metadata_BE.repository.MarkerRepository;
import com.tvu.Metadata_BE.repository.SessionRepository;
import com.tvu.Metadata_BE.stub.MarkerStub;

@RunWith(MockitoJUnitRunner.class)
public class MetaData_ProjectTest {

	@InjectMocks
	private MetaDataController controllerUT = new MetaDataController();

	@InjectMocks
	private MarkerStub markerStudbUT;

	@Mock
	private MarkerRepository markerepo;

	@Mock
	private SessionRepository sessionRepoTest;

	@Test
	public void paginationMarkerTest() throws Exception {
		List<Marker> marker = new ArrayList<>();
		Marker t1 = new Marker();
		t1.setId("1");
		t1.setSessionId("sessionId1");
		t1.setRecordId("RecordId1");
		t1.setEndTimeStamp(13847382L);
		t1.setSourceId("SourceId1");
		t1.setSourceSharedMemoryName("name1");
		t1.setStartTimeStamp(33434234L);
		t1.setTitle("My Title 1");
		t1.setUserId("TestUser1");
		marker.add(t1);
		Page<Marker> markerPage = new PageImpl<>(marker);
		Pageable paging = PageRequest.of(0, 1);
		when(markerepo.findAll(paging)).thenReturn(markerPage);
		Page<Marker> markerPageResult = markerepo.findAll(paging);
		assertEquals(markerPageResult.getNumberOfElements(), 1);

	}

	@Test
	public void getMarkerbyProductionidTest() throws Exception {
		Session session = new Session();
		session.setId("sadfa343we");
		session.setProductionid(1L);
		session.setDescription("Description1");
		session.setEndTime(87384738L);
		session.setPlaceRootPath("RootPath");
		session.setProducedBy("Test Prducer");
		session.setProgramid(3433L);
		session.setProgramname("Test Programme");
		session.setDescription("Test Description");
		session.setEndTime(3463463L);
		session.setProductionid(37437L);
		session.setTitle("Test Title");
		session.setStatus(200);
		session.setStartTime(34343L);

		Records record = new Records();
		record.setId("Record Id");
		Set<Records> set1 = new HashSet<>();
		set1.add(record);
		session.setRecords(set1);

		Set<Sources> set2 = new HashSet<>();
		Sources sources1 = new Sources();
		sources1.setId("1");
		sources1.setName("SourceTestName1");
		sources1.setStartTime(12345l);
		sources1.setSession(session);
		sources1.setStopTime(4321L);

		set2.add(sources1);
		session.setSources(set2);
		Set<ShortStories> set3 = new HashSet<>();
		ShortStories shortStories = new ShortStories();
		shortStories.setAurthor("Test Auther");
		shortStories.setCreatedAt(1234L);
		shortStories.setFrontCover("FrontCover Test");
		shortStories.setId("Test Id");
		Set<Scene> sceneSet = new HashSet<>();
		Scene scene = new Scene();
		scene.setShortStory(shortStories);
		scene.setSourceid("Test SourceId");
		scene.setStartTimeStamp(1234L);
		scene.setStopTimeStamp(1234L);
		shortStories.setScenes(sceneSet);
		shortStories.setTag("Test Tag");
		shortStories.setTitle("Test Title");
		set3.add(shortStories);

		session.setShortStories(set3);
		when(sessionRepoTest.findByProductionid(1L)).thenReturn(session);
		Session responseSession = sessionRepoTest.findByProductionid(1L);
		assertEquals(responseSession.getProgramid(), new Long("3433"));

		when(sessionRepoTest.findByUsingTitleLikeProductionid(1L)).thenReturn(session);
		Session responseLikeSession = sessionRepoTest.findByUsingTitleLikeProductionid(1L);
		assertEquals(responseLikeSession.getProgramid(), new Long("3433"));

	}
	
	@Test
	public void getMarkerbySourceidTest() throws Exception {
		List<Marker> marker = new ArrayList<>();
		Marker t1 = new Marker();
		t1.setId("1");
		t1.setSessionId("sessionId1");
		t1.setRecordId("RecordId1");
		t1.setEndTimeStamp(13847382L);
		t1.setSourceId("SourceId1");
		t1.setSourceSharedMemoryName("name1");
		t1.setStartTimeStamp(33434234L);
		t1.setTitle("My Title 1");
		t1.setUserId("TestUser1");
		marker.add(t1);
		when(markerepo.findBySourceId("SourceId1")).thenReturn(marker);
		List<Marker> repo = markerepo.findBySourceId("SourceId1");
		assertEquals("SourceId1",repo.stream()
				.filter(val->val.getSourceId()
				.equals("SourceId1"))
				.findAny()
				.get()
				.getSourceId());

	}
}
