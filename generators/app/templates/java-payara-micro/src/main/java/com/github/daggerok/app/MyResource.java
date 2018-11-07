package com.github.daggerok.app;

import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Log
@Path("")
@ApplicationScoped
//@Path("{path: .*?}")
@Produces(APPLICATION_JSON)
public class MyResource {

  @Inject MyRepository myRepository;

  @POST
  @Consumes(APPLICATION_JSON)
  public Response createPerson(Map<String, String> request) {
    myRepository.addString(request.getOrDefault("string", ""));
    log.info("ololo");
    return Response.accepted().build();
  }

  @GET
  public Collection<String> getAllPeople() {
    log.info("trololo");
    return myRepository.getStrings();
  }
}
