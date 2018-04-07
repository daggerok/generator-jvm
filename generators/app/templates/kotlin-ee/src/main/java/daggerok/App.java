package daggerok;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@Stateless
//@ApplicationScoped
@ApplicationPath("")
@Produces(APPLICATION_JSON)
public class App extends Application {

  @Context
  UriInfo uriInfo;

  @GET
  @Path("")
  public Response index(@Context final UriInfo uriInfo) {

    return Response.ok(Json.createArrayBuilder()
                           .add(uriInfo.getBaseUriBuilder()
                                       .path(App.class)
                                       .path(App.class, "index")
                                       .build()
                                       .toString())
                           .add(uriInfo.getBaseUriBuilder()
                                       .path(App.class)
                                       .path(App.class, "health")
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
                                                .path(App.class)
                                                .path(App.class, "health")
                                                .build()
                                                .toString())
                           .build())
                   .build();
  }
}
