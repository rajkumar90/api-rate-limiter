package com.sample.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/sample")
@Consumes(RateLimiterConstants.APPLICATION_JSON)
@Produces(RateLimiterConstants.APPLICATION_JSON)
public class SampleService {
	
	@Autowired
	RateLimiter rateLimiter;
	
	@GET
	@Path("/hello")
	@Produces("application/text")
	public String sayHello(@HeaderParam(RateLimiterConstants.CLIENT_ID) String clientId) {
		String apiName = "SayHello";
		if (!rateLimiter.withinLimit(clientId, apiName))
			throw new RuntimeException();			
		
		rateLimiter.incrementHitCount(clientId, apiName);
		return RateLimiterConstants.HELLO_STRING;
			
	}
}
