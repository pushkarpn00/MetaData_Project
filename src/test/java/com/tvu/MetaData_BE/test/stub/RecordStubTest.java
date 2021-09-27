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
import com.tvu.Metadata_BE.Model.DownloadTask;
import com.tvu.Metadata_BE.Model.Records;
import com.tvu.Metadata_BE.Model.Session;
import com.tvu.Metadata_BE.controller.MetaDataController;
import com.tvu.Metadata_BE.dto.RecordInfoDTO;
import com.tvu.Metadata_BE.dto.RecordInfoRecordDTO;
import com.tvu.Metadata_BE.dto.ResponseRecordInfo;
import com.tvu.Metadata_BE.dto.ResponseV2RecordInfo;
import com.tvu.Metadata_BE.dto.TaskDTO;
import com.tvu.Metadata_BE.repository.DownloadTaskRepository;
import com.tvu.Metadata_BE.repository.RecordRepository;
import com.tvu.Metadata_BE.repository.SourceRepository;
import com.tvu.Metadata_BE.stub.MarkerStub;
import com.tvu.Metadata_BE.util.CommonUtility;

@RunWith(MockitoJUnitRunner.class)
public class RecordStubTest {

	@InjectMocks
	private MetaDataController controllerUT = new MetaDataController();

	@InjectMocks
	private MarkerStub markerStudbUT;

	@Mock
	private DownloadTaskRepository downloadTaskRepository;

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
	
	@Test 
	public void getV2RecordInfoTest() throws Exception {
		ResponseV2RecordInfo v2recordinfo=new ResponseV2RecordInfo();
		v2recordinfo.setSessionid("");
		v2recordinfo.setSourceid("");
		v2recordinfo.setErrorCode(Constants.API.SUCCESS_CODE);
		v2recordinfo.setErrorMessage(Constants.API.SUCCESS_MESSAGE_GET);
		RecordInfoRecordDTO recordInfo = new RecordInfoRecordDTO();
		recordInfo.setID("TestRecordId");
		recordInfo.setTitle("Test");
		List<String> listValue=new ArrayList<>();
		listValue.add("TestTag1");
		listValue.add("TestTag2");
		listValue.add("TestTag3");
		
		recordInfo.setTags(listValue);
		recordInfo.setStartTimeStamp(1349238726L);
		recordInfo.setEndTimeStamp(1349238726L);
		recordInfo.setSize("TestSize");
		
		TaskDTO taskDtoTest = new TaskDTO();
		taskDtoTest.setTaskid("TestTaskId");
		taskDtoTest.setStatus(Constants.API.Normal);
		taskDtoTest.setStartTimeStamp(1346383858L);
		taskDtoTest.setEndTimeStamp(1396383853L);
		List<TaskDTO> listTaskDTO = new ArrayList<>();
		listTaskDTO.add(taskDtoTest);
		recordInfo.setTasks(listTaskDTO);
		DownloadTask downloadTasks = new DownloadTask();
		downloadTasks.setCreatetime(1368384838L);
		downloadTasks.setDeletetime(1368384838L);
		downloadTasks.setEndtime(1368384838L);
		downloadTasks.setFilename("TestFileName");
		downloadTasks.setId(1L);
		downloadTasks.setKeepduration(1234);
		downloadTasks.setMergedtime(1368384838L);
		downloadTasks.setRecordid("TestRecordedId");
		downloadTasks.setSessionid("TestSessionId");
		downloadTasks.setSourceid("TestSourceId");
		downloadTasks.setStarttime(1368384838L);
		downloadTasks.setStatus(0);
		downloadTasks.setTaskid("TestTaskId");
		downloadTasks.setUserid("TestUserId");
		List<DownloadTask> downloadTasksList = new ArrayList<>();
		
		downloadTasksList.add(downloadTasks);
		
		List<RecordInfoRecordDTO> listRecordInforRecord=new ArrayList<RecordInfoRecordDTO>();
		listRecordInforRecord.add(recordInfo);
		v2recordinfo.setRecordinfo(listRecordInforRecord);
		
		
		when(downloadTaskRepository.getDownloadByRecordAndSession("TestSourceId", "202005080318-1fa4c507-618c-4033-877d-4d08b956677b")).thenReturn(downloadTasksList);
		List<DownloadTask> listRecordsRes = downloadTaskRepository.getDownloadByRecordAndSession("TestSourceId", "202005080318-1fa4c507-618c-4033-877d-4d08b956677b");
		assertNotNull(listRecordsRes);
		
		
		
	}
	
}
