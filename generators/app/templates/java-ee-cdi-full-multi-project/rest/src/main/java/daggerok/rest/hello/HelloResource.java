package daggerok.rest.hello;

import daggerok.ejb.api.HelloService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@Stateless
@Produces(APPLICATION_JSON)
public class HelloResource {

  @Inject
  HelloService helloService;

  @POST
  @Path("")
  public Response hello(final Map<String, String> params) {

    final String name = params.getOrDefault("name", "Buddy");

    return Response.ok(Json.createObjectBuilder()
                           .add("message", helloService.sayHello(name))
                           .build()
                           .toString())
                   .build();
  }
}
