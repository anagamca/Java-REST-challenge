package com.interview.core.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Health check endpoint
 */
@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
public class StatusRestService {
    private static Logger logger = LoggerFactory.getLogger(StatusRestService.class);

    @GET
    public Response getStatus() {
        //TODO 1: print that this endpoint was called to the console. Return a status message of 200 okay
        throw new NotImplementedException();
    }
}
