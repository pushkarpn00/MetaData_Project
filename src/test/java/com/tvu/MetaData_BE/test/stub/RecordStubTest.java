package com.tvu.MetaData_BE.test.stub;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.tvu.Metadata_BE.Constants;
import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.controller.MetaDataController;
import com.tvu.Metadata_BE.dto.RecordInfoDTO;
import com.tvu.Metadata_BE.dto.ResponseRecordInfo;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.SourceRepository;
import com.tvu.Metadata_BE.stub.MarkerStub;

@RunWith(MockitoJUnitRunner.class)
public class RecordStubTest {

	@InjectMocks
	private MetaDataController controllerUT = new MetaDataController();

	@InjectMocks
	private MarkerStub markerStudbUT;

	@Mock
	private SourceRepository sourceRepository;

	@Mock
	private RecordRepository recordrepo;
	
	@Test
	public void getRecordInfoTest() throws Exception {
		ResponseRecordInfo responseRecordInfoTest = new ResponseRecordInfo();
		responseRecordInfoTest.setSessionid("202005080318-1fa4c507-618c-4033-877d-4d08b956677b");
		responseRecordInfoTest.setErrorCode(Constants.API.SUCCESS_CODE);
		responseRecordInfoTest.setErrorMessage(Constants.API.SUCCESS_MESSAGE_GET);
		List<RecordInfoDTO> recordInfoDTOList=new ArrayList<RecordInfoDTO>();
		
		RecordInfoDTO recordInfoDTO=new RecordInfoDTO();
		recordInfoDTO.setID("TestSourceId");
		recordInfoDTO.setName("TestName");
		recordInfoDTO.setStatus(200);
		recordInfoDTO.setStartTimeStamp(123456L);
		recordInfoDTO.setEndTimeStamp(123456L);
		recordInfoDTO.setSize("TestSize");
		recordInfoDTOList.add(recordInfoDTO);
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
		List<Records> listRecords = new ArrayList<Records>();
		listRecords.add(rec);
		when(recordrepo.getAllRecordBySourceidAndSessionIDAndEndtime("TestSourceId", "202005080318-1fa4c507-618c-4033-877d-4d08b956677b")).thenReturn(listRecords);
		List<Records> listRecordsRes = recordrepo.getAllRecordBySourceidAndSessionIDAndEndtime("TestSourceId", "202005080318-1fa4c507-618c-4033-877d-4d08b956677b");
		assertNotNull(listRecordsRes);
		
		responseRecordInfoTest.setRecordinfo(recordInfoDTOList);
		assertNotNull(responseRecordInfoTest);
	}
	
}
