package com.wsl.ws;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface FetchDBData {
	
	 @WebResult(name="ID")
	public Object getUserDetails(@WebParam(name="ID")String ID);

}
