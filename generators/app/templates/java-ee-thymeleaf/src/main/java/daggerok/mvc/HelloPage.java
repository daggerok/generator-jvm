package daggerok.mvc;

import io.vavr.collection.List;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_HTML;

@Path("")
@Controller
@Produces(TEXT_HTML)
public class HelloPage {

  @Inject Models models;

  @GET
  @Path("")
  public String helloView() {
    models.put("message", "Hello, World!");
    models.put("data", List.of("ololo", "trololo").toJavaList());
    return "hello";
  }
}
