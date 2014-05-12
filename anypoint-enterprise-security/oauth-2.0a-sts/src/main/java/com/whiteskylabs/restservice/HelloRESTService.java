package com.whiteskylabs.restservice;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
 
/**
 * Class demonstrating REST component in Mule ESB
 * 
 * @author Middleware School (http://training.middlewareschool.com/mule)
 *
 */
@Path("/")
public class HelloRESTService {
  
	@GET
	@Path("hello/{name}")
	public String sayHello(@PathParam("name") String name) {
		return "Hello " + name + "!";
	}
}
