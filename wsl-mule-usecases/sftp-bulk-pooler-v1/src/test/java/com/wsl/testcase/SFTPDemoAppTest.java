package com.wsl.testcase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

public class SFTPDemoAppTest extends FunctionalMunitSuite {

	@Override
	protected String getConfigResources() {
		return "sftp-demo-app.xml";
	}

	/**
	 * 
	 */
	private void mockSftp() {
		whenMessageProcessor("outbound-endpoint").ofNamespace("sftp")
				.thenReturnSameEvent();
	}

	/**
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private List<Map<String, Object>> getPollResults()
			throws UnsupportedEncodingException {

		List<Map<String, Object>> cmprsdFilesCollection = new ArrayList<Map<String, Object>>();
		Map<String, Object> fileContentsinMap = new HashMap<String, Object>();

		// Creating File contents
		String file1Contents = "File one contents";
		String file2Contents = "File two contents";

		// Creating Map Object with file names and contents
		fileContentsinMap.put("samplefile1.txt", new ByteArrayInputStream(
				file1Contents.getBytes("UTF-8")));
		fileContentsinMap.put("sampleFile2.txt", new ByteArrayInputStream(
				file2Contents.getBytes("UTF-8")));

		// Adding the map object of file contents to collection
		cmprsdFilesCollection.add(fileContentsinMap);

		// returns the collection
		return cmprsdFilesCollection;
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSFTPDemoAppCompleteFlow() throws Exception {

		// mocking the endpoint with URL
		mockSftp();

		System.out.println("getPollResults(): " + getPollResults());

		// invokes first flow sftp-demo-appFlow1
		// getPollResults() is a method which set message
		MuleEvent responseFirstFlow = runFlow("sftp-demo-appFlow1",testEvent(getPollResults()));
		
		System.out.println("responseFirstFlow.getMessage(): "+responseFirstFlow.getMessage());
		System.out.println("responseFirstFlow.getMessage().getPayloadAsString(): "+responseFirstFlow.getMessage().getPayloadAsString());
		
		// invokes flow sftp-demo-appFlow1
		// getPollResults() is a method which set message
		MuleEvent resultEvent = runFlow("sftp-demo-appFlow2",testEvent(responseFirstFlow.getMessage().getPayload()));

		System.out.println("message-payload: "+ resultEvent.getMessage().getPayload());
		
		System.out.println(resultEvent.getMessage().getPayload().getClass().toString());

		// asserting
		assertNotNull(resultEvent.getMessage().getPayload());
		assertNotSame("class org.mule.transport.NullPayload", resultEvent.getMessage().getPayload().getClass().toString());

	}

	public byte[] compress(byte[] content) {

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			GZIPOutputStream gzipOutputStream = new GZIPOutputStream(
					byteArrayOutputStream);
			gzipOutputStream.write(content);
			System.out.println("gzipOutputStream : " + gzipOutputStream);
			gzipOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return byteArrayOutputStream.toByteArray();
	}
}
