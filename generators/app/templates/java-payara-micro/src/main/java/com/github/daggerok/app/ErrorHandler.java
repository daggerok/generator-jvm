package com.github.daggerok.app;

import lombok.extern.java.Log;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Log
@Provider
public class ErrorHandler implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(final Throwable exception) {
    final String message = exception.getLocalizedMessage();
    log.severe(() -> format("Unexpected error: %s", message));
    return Response.status(BAD_REQUEST)
                   .header(ACCEPT, APPLICATION_JSON)
                   .header(CONTENT_TYPE, APPLICATION_JSON)
                   .entity(singletonMap("error", message))
                   .build();
  }
}
