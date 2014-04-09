package org.mule.transport.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;

public class GZIPDecompress {

	public byte[] decompress(byte[] contentBytes){
		
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 // TODO
	        try{
	            IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(contentBytes)), out);
	        } catch(IOException e){
	            throw new RuntimeException(e);
	        }
	        return out.toByteArray();
	}
	
}
