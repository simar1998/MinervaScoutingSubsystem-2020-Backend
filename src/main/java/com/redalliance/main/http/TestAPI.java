package com.redalliance.main.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 * -------------------------------------
 * This class contains the test api class so you can call the endpoint /api/test/get
 **/
@Path("/test")
public class TestAPI {

    Logger logger = LoggerFactory.getLogger(TestAPI.class);

    static {
        Logger logger = LoggerFactory.getLogger(TestAPI.class);
        logger.info("REST SERVICES ARE OPERATIONAL !!!");
    }

    @GET
    @Path("/get")
    @Produces("text/plain")
    public Response testGet(@Context Request request) {
        logger.info("GET called  /api/test/get ----> " + request.getMethod());
        return Response.ok("GET REQUEST SUCCESSFUL").build();
    }


}
