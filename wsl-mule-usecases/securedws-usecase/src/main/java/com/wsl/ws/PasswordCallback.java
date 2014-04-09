/*
 * $Id: PasswordCallback.java 18408 2010-12-09 19:44:58Z travis.carlson $
 * --------------------------------------------------------------------------------------
 *
 * (c) 2003-2010 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */

package com.wsl.ws;

import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class PasswordCallback implements CallbackHandler
{
	 private String user = "wsluser";
	 private String pwd = "wslpwd";
	 
	 
	 public void handle (Callback[] callbacks) throws IOException, UnsupportedCallbackException
	    {
		 System.out.println(">>>>>>>>>INSIDE CALLBACK ");
	        for (int i = 0; i < callbacks.length; i++)
	        {
	            if (callbacks[i] instanceof WSPasswordCallback)
	            {
	                WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
	                System.out.println(">>>>>>>>>Username: "+pc.getIdentifier());
	                System.out.println(">>>>>>>>>Password: "+pc.getPassword());

	                if (! user.equals(pc.getIdentifier())){
	                        throw new IOException(">>>>>>>>>>>>>>>>>unknown user: "+pc.getIdentifier());
	                }else{
	                	System.out.println("Inside set password mehod>>>>>>>>>>>>>>>>>>>>>>>>>");
	                    pc.setPassword(pwd);
	                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Password: "+pc.getPassword());
	                }
	            }
	        }
	    }
	 
	 
}

