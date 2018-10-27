package daggerok.rest;

import daggerok.events.DomainEvent;
import daggerok.events.MessageCreated;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.UUID;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Stateless
@Path("/api/v1")
@Produces(APPLICATION_JSON)
public class MessageResource {

  @GET
  @Path("/hello")
  public DomainEvent hello() {
    return MessageCreated.of(UUID.randomUUID(), "Hello, Random!");
  }

  @GET
  @Path("/hello/{uuid}")
  public DomainEvent hello(@PathParam("uuid") final UUID uuid) {
    return MessageCreated.of(uuid, format("Hello, %s", uuid));
  }
}
