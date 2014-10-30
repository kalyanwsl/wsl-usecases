package org.mule.transport.file;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.transport.AbstractMuleMessageFactory;


public class MTUFileMessageFactory extends AbstractMuleMessageFactory {

	public MTUFileMessageFactory(MuleContext context) {
		super(context);
		
	}
	
	@Override
    protected Class<?>[] getSupportedTransportMessageTypes()
    {
        return new Class[]{InputStream.class, File.class, List.class};
    }
	
	@Override
    protected Object extractPayload(Object transportMessage, String encoding) throws Exception
    {
        return transportMessage;
    }
	
	 @Override
	    protected void addProperties(DefaultMuleMessage message, Object transportMessage) throws Exception
	    {
	        super.addProperties(message, transportMessage);
	       
	    }

}
