package com.redalliance.main.http;

import com.redalliance.main.db.models.SubmittedInfoWrapper;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * @author Simar Pal Kalsi
 * Live Long And Prosper -----> Simt'pal
 **/
@Path("/upload")
public class MatchDataAPI {

    @POST
    @Path("/submittedInfoWrapper")
    public Response processSubmittedInfo(MultipartFormDataInput multipartFormDataInput) throws IOException {

        String dataToBeParsed = multipartFormDataInput.getFormDataPart("data", String.class,null);
        String dataType = multipartFormDataInput.getFormDataPart("dataType",String.class, null);

        if (dataType.equals("protoByte")){
            SubmittedInfoWrapper.processProtoString(dataToBeParsed);
        }
        else if (dataType.equals("GSON")){
            SubmittedInfoWrapper.processIncommingString(dataToBeParsed);
        }

        return Response.ok("Submitted info wrapper object added into database for processing").build();
    }
}
