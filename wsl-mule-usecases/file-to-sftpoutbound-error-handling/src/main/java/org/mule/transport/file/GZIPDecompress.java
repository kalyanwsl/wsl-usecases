package org.mule.transport.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;

public class GZIPDecompress {
	
	public byte[] decompress(Map<String, Object> fileContent){
		
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
	        try{
	        	byte[] contentBytes = (byte[])fileContent.get("fileData");
	            IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(contentBytes)), out);
	        } catch(IOException e){
	            throw new RuntimeException(e);
	        }
	        return out.toByteArray();
	}
	
}
