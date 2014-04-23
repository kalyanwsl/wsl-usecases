package com.wsl.test.munit;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

public class MunitFileToVmTest extends FunctionalMunitSuite {

	@Override
	protected String getConfigResources() {
		// TODO Auto-generated method stub
		return "src/main/app/mule-file-messageprocess-production.xml";
	}

	@Test
	public void testpollfilesflow() throws Exception {
		mockVM();
		System.out.println("--1--");
		MuleEvent getResponse = runFlow("poll-files-flow",
				testEvent(getfilepayload()));
		System.out.println("--2--");
		assertNotNull(getResponse.getMessage().getPayload());
		assertEquals("class java.util.HashMap", getResponse.getMessage()
				.getPayload().getClass().toString());
		assertNotSame("class org.mule.module.json.JsonData", getResponse
				.getMessage().getPayload().getClass().toString());
	}
	
	
	@Test
	public void testMoveVmFiledataToSftpFlow() throws Exception {
		mockSftp();
		System.out.println("--3--");
		MuleEvent getResponse = runFlow("move-vm-filedata-to-sftp-flow",
				testEvent(vmData()));
		System.out.println("--4--");
		assertNotNull(getResponse.getMessage().getPayload());
		assertEquals("class java.lang.String", getResponse.getMessage()
				.getPayload().getClass().toString());
		assertNotSame("class org.mule.module.json.JsonData", getResponse
				.getMessage().getPayload().getClass().toString());
	}

	
	
	@Test
	public void testCompleteFlow() throws Exception {
		mockSftp();
		System.out.println("--5--");
		MuleEvent getResponse1 = runFlow("poll-files-flow",
				testEvent(getfilepayload()));
		
		MuleEvent getResponse = runFlow("move-vm-filedata-to-sftp-flow",
				testEvent(getResponse1.getMessage()));
		System.out.println("--6--");
		assertNotNull(getResponse.getMessage().getPayload());
		assertEquals("class java.lang.String", getResponse.getMessage()
				.getPayload().getClass().toString());
		
		System.out.println("reponse::::::::::::::::::"+getResponse.getMessage().getPayload());
		assertNotSame("class org.mule.module.json.JsonData", getResponse
				.getMessage().getPayload().getClass().toString());
	}

	private void mockSftp() {
		whenMessageProcessor("outbound-endpoint").ofNamespace("sftp")
				.thenReturnSameEvent();
	}
	
	
	private void mockVM() throws UnsupportedEncodingException {
		System.out.println("--------------mock vm----------");
		whenEndpointWithAddress("vm:/fileDataQueue").thenReturn(
				muleMessageWithPayload(vmMockresponse()));
	}

	private Map vmMockresponse() throws UnsupportedEncodingException {
		Map<String, Object> h = new HashMap();
		h.put("file-name", "1");
		String filecontent="11";
		byte[] bArr=compress(filecontent.getBytes());	
		h.put("file-content", bArr);
		return h;
	}
	
	
	
	private Map vmData() throws UnsupportedEncodingException {
		Map<String, Object> h = new HashMap();
		h.put("file-name", "1");
		String filecontent="11";
		byte[] bArr=compress(filecontent.getBytes());		
		h.put("file-content", bArr);
		return h;
	}

	private List<Map> getfilepayload() throws UnsupportedEncodingException {

		List<Map> ls = new ArrayList<Map>();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("file-name", "1");		
		String filecontent="11";
		byte[] bArr=compress(filecontent.getBytes());
		m.put("file-content", bArr);
		ls.add(m);
		return ls;
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
		System.out.println("ACTUAL CONTENT size:"
				+ byteArrayOutputStream.size());
		System.out.printf("Compression ratio %f\n",
				(1.0f * content.length / byteArrayOutputStream.size()));
		return byteArrayOutputStream.toByteArray();
	}
	
}
