package daggerok.rest.error;

import io.vavr.Function3;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.URL;

import static java.lang.String.format;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Slf4j
@Provider
public class JaxRsExceptionMapper implements ExceptionMapper<Throwable> {

  @Context UriInfo uriInfo;

  static final Function3<UriInfo, String, String, String> url = (uriInfo, method, path) -> {
    Try<URL> tryUrl = Try.of(() -> uriInfo.getBaseUriBuilder().build().toURL());
    if (tryUrl.isFailure()) return format("%s %s", method, path);

    URL url = tryUrl.get();
    return format("%s %s://%s%s", method, url.getProtocol(), url.getAuthority(), path);
  };

  public Response toResponse(Throwable e) {

    String error = format("%s: %s", e.getClass().getSimpleName(), e.getLocalizedMessage());
    //log.error("handling fallback: {}", error, e);
    log.error("handling fallback: {}", error);

    JsonArray resources = Json.createArrayBuilder()
                              .add(url.apply(uriInfo, "GET", "/api/v1/hello"))
                              .add(url.apply(uriInfo, "GET", "/api/v1/hello/{uuid}"))
                              .build();

    return Response.status(BAD_REQUEST)
                   .entity(Json.createObjectBuilder()
                               .add("resources", resources)
                               .add("error", error)
                               .build())
                   .build();
  }
}
