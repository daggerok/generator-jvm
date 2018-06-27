package daggerok.health;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class HealthResource {

  @Context UriInfo uriInfo;

  @GET
  @Path("")
  public Response index(@Context final UriInfo uriInfo) {
    return Response.ok(Json.createObjectBuilder()
                           .add("_self", uriInfo.getBaseUriBuilder()
                                                .path(HealthResource.class)
                                                .path(HealthResource.class, "index")
                                                .build()
                                                .toString())
                           .build())
                   .build();
  }

  @GET
  @Path("health")
  public Response health() {
    return Response.ok(Json.createObjectBuilder()
                           .add("status", "UP")
                           .add("_self", uriInfo.getBaseUriBuilder()
                                                .path(HealthResource.class)
                                                .path(HealthResource.class, "health")
                                                .build()
                                                .toString())
                           .build())
                   .build();
  }
}
