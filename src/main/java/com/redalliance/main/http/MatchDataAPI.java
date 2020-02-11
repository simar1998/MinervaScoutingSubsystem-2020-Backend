package com.redalliance.main.http;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Path("/upload")
public class MatchDataAPI {

    @POST
    @Path("/submittedInfoWrapper")
    public Response processSubmittedInfo(){

        return Response.ok("Submitted info wrapper object added into database for processing").build();
    }
}
