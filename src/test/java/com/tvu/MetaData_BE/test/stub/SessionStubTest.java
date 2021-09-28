package com.tvu.MetaData_BE.test.stub;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.controller.MetaDataController;
import com.tvu.Metadata_BE.dto.ResponseSessionInfo;
import com.tvu.Metadata_BE.dto.SessionDTO;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.SessionRepository;
import com.tvu.Metadata_BE.stub.MarkerStub;

@RunWith(MockitoJUnitRunner.class)
public class SessionStubTest {

	@InjectMocks
	private MetaDataController controllerUT = new MetaDataController();

	@InjectMocks
	private MarkerStub markerStudbUT;

	@Mock
	private SessionRepository sessionrepo;
	
	@Mock
	private Session sessionUT;

	@Mock
	private RecordRepository recordrepo;
	
	@Test
	public void getSessionInfoTest() throws Exception {
		
		ResponseSessionInfo sessioninfo=new ResponseSessionInfo();
		sessioninfo.setTotal_count(10);
		sessioninfo.setCount(10);
		sessioninfo.setErrorCode(Constants.API.SUCCESS_CODE);
		sessioninfo.setErrorMessage(Constants.API.SUCCESS_MESSAGE_GET);
		sessioninfo.setUserid("TestUserId");
		SessionDTO sessionDto = new SessionDTO();
		sessionDto.setDescription("Test Description");
		sessionDto.setEndTime(1272637383L);
		sessionDto.setId("TestId");
		sessionDto.setPlaceRootPath("Test Place Root Path");
		sessionDto.setProducedBy("TestProduceBy");
		sessionDto.setProductionid(1827383839L);
		sessionDto.setProgramid(1827383839L);
		sessionDto.setProgramname("TestPorgramme");
		sessionDto.setSize("Test Size");
		sessionDto.setStartTime(103839392L);
		sessionDto.setStatus(1);
		sessionDto.setTitle("Set Test Title");
		
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
		List<Session> sessions=new ArrayList<>();
		sessions.add(session);
		when(sessionrepo.findUsingProducedBy(session.getProducedBy())).thenReturn(sessions);
		
		List<Session> listRecordsRes = sessionrepo.findUsingProducedBy(session.getProducedBy());
		assertNotNull(listRecordsRes);
		
		List<SessionDTO> sessionDTOList = new ArrayList<>();
		sessionDTOList.add(sessionDto);
		sessioninfo.setSessionInfo(sessionDTOList);
		assertNotNull(sessionDTOList);
		assertNotNull(sessioninfo);
	}
	
	@Test 
	public void getParamsInfoTest() throws Exception {
		Session session = new Session();
		session.setId("sadfa343we");
		session.setDescription("Description1");
		session.setEndTime(87384738L);
		session.setPlaceRootPath("RootPath");
		session.setProducedBy("Test Prducer");
		session.setProgramid(3433L);
		session.setProgramname("Test Programme");
		session.setProductionid(37437L);
		session.setTitle("Test Title");
		session.setStatus(200);
		session.setStartTime(34343L);
		Optional<Session> opt = Optional.of(session);
		when(sessionrepo.findById(session.getId())).thenReturn(opt);
		
		

		Optional<Session> opssession=sessionrepo.findById(session.getId());
		assertThat(opssession.get().getId()).isSameAs("sadfa343we");
		assertThat(opssession.get().getDescription()).isSameAs("Description1");
		assertThat(opssession.get().getPlaceRootPath()).isSameAs("RootPath");
		assertThat(opssession.get().getProducedBy()).isSameAs("Test Prducer");
		assertThat(opssession.get().getProgramname()).isSameAs("Test Programme");
		assertThat(opssession.get().getDescription()).isSameAs("Description1");

		
	}
	
}
