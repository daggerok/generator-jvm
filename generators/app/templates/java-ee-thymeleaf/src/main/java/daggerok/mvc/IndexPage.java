package daggerok.mvc;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static java.util.Arrays.asList;
import static javax.ws.rs.core.MediaType.TEXT_HTML;

@Path("")
public class IndexPage {

  @Inject Models models;

  @GET
  @Path("")
  @Controller
  @Produces(TEXT_HTML)
  public String indexView() {
    models.put("message", "Hello, World!");
    models.put("data", asList("ololo", "trololo"));
    return "index";
  }
}
