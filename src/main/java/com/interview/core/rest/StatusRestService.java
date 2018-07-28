package com.interview.core.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Health check endpoint
 */
@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
public class StatusRestService {
    private static Logger logger = LoggerFactory.getLogger(StatusRestService.class);

    @GET
    public Response getStatus() {
    	logger.info("server is healthy");
        return Response.ok().build();
    }
}
