package daggerok.app;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Slf4j
@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class AppResource {

  @Inject JsonService jsonService;

  @GET
  @Path("")
  public Response index(@Context final UriInfo uriInfo) {

    log.info("O.o");

    return Response.ok(jsonService.jsongify(uriInfo,
                                            AppResource.class, "index")
                                  .build())
                   .build();
  }
}
