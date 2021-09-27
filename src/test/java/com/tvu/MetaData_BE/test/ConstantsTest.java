package com.tvu.MetaData_BE.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import com.tvu.Metadata_BE.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ConstantsTest {

	
	@InjectMocks
	private Constants.API api;
	
	@Test
	public void testConstantsClassHasNoPublicConstructor() throws NoSuchMethodException, SecurityException{
		Constructor<Constants> constructors = Constants.class.getDeclaredConstructor();
		assertFalse(Modifier.isPrivate(constructors.getModifiers()));
	}
	
	@Test
	public void testConstantsClassHasNoProtectedConstructor() throws NoSuchMethodException, SecurityException{
		Constructor<Constants> constructors = Constants.class.getDeclaredConstructor();
		assertFalse(Modifier.isProtected(constructors.getModifiers()));
	}
	
	@Test
	public void testConstantNAME_API() {
		assertEquals("MetaData", api.NAME_API);
	}
	
	@Test
	public void testConstantSUCCESS_CODE() {
		assertEquals("0x0", api.SUCCESS_CODE);
	}
	
	@Test
	public void testConstantERROR_CODE() {
		assertEquals("0x5", api.ERROR_CODE);
	}
	
	@Test
	public void testConstantSUCCESS_MESSAGE() {
		assertEquals("OK", api.SUCCESS_MESSAGE);
	}
	
	@Test
	public void testConstantFAIL_MESSAGE() {
		assertEquals("Failed", api.FAIL_MESSAGE);
	}
	
	@Test
	public void testConstantINVALID_PARAMETER() {
		assertEquals("Invaild Parameters Values", api.INVALID_PARAMETER);
	}
	
	@Test
	public void testConstantSUCCESS_MESSAGE_GET() {
		assertEquals("OK", api.SUCCESS_MESSAGE_GET);
	}
	
	@Test
	public void testConstantSUCCESS_DOWNLOAD_MESSAGE() {
		assertEquals("Downloaded", api.SUCCESS_DOWNLOAD_MESSAGE);
	}
	
	@Test
	public void testConstantERROR_DOWNLOAD_MESSAGE() {
		assertEquals("Unable to download", api.ERROR_DOWNLOAD_MESSAGE);
	}
	
	@Test
	public void testConstantPROGRESS_OPERATION_CODE() {
		assertEquals("9001", api.PROGRESS_OPERATION_CODE);
	}
	
	@Test
	public void testConstantPASS_FAIL_OPERATION_CODE() {
		assertEquals("9002", api.PASS_FAIL_OPERATION_CODE);
	}
	
	@Test
	public void testConstantTaskNOtFound() {
		assertEquals("Task id not found", api.TaskNOtFound);
	}
	
	@Test
	public void testConstantFailed() {
		assertEquals(0, api.Failed);
	}
	
	@Test
	public void testConstantMerge() {
		assertEquals(1, api.Merge);
	}
	
	@Test
	public void testConstantReady() {
		assertEquals(2, api.Ready);
	}
	
	@Test
	public void testConstantDelete() {
		assertEquals(3, api.Delete);
	}
	
	@Test
	public void testConstantNormal() {
		assertEquals(4, api.Normal);
	}
	
	@Test
	public void testConstanttaskidnotfound() {
		assertEquals(5, api.taskidnotfound);
	}
	
	@Test
	public void testConstantlogger() {
		assertEquals("debug.log", api.logger);
	}
	
}
