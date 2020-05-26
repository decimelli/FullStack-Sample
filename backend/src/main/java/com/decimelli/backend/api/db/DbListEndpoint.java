package com.decimelli.backend.api.db;

import com.decimelli.backend.service.MongoService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("db")
@Singleton
public class DbListEndpoint {

    @Inject
    MongoService mongodb;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDBs() {
        return Response.ok(mongodb.getAllDbs(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{dbname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDb(@PathParam("dbname") String dbname) {
        return Response.ok(mongodb.getDb(dbname), MediaType.APPLICATION_JSON).build();
    }
}
