package com.redalliance.main.http;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;

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

    @GET
    @Path("/hi")
    @Produces("text/plain")
    public Response testGet1(@Context Request request) {
        logger.info("GET called  /api/test/get ----> " + request.getMethod());
        return Response.ok("HELLO LESLEY!!").build();
    }

    @POST
    @Path("/add")
    @Produces("text/plain")
    public Response testGet12(MultipartFormDataInput multipartFormDataInput) throws IOException {
        Integer num1 = multipartFormDataInput.getFormDataPart("num",Integer.class,null);
        return Response.ok(num1 + num1 ).build();
    }


}
