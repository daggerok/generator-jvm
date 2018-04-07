package daggerok.servlet;

import daggerok.ejb.api.HelloService;
import lombok.Cleanup;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@WebServlet(urlPatterns = "/api/*")
public class HelloServlet extends HttpServlet {

  private static final long serialVersionUID = -3757579368400077692L;

  @Inject
  HelloService helloService;

  @Override
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    @Cleanup final PrintWriter writer = response.getWriter();
    final String nameParameter = request.getParameter("name");
    final String name = null == nameParameter ? "Buddy" : nameParameter;

    writer.println(Json.createObjectBuilder()
                       .add("message", helloService.sayHello(name))
                       .build()
                       .toString());

    response.setContentType(APPLICATION_JSON);
  }
}
