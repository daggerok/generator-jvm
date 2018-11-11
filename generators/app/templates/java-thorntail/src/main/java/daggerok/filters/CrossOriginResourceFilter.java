package daggerok.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CrossOriginResourceFilter implements ContainerResponseFilter {

  @Override
  public void filter(final ContainerRequestContext request, final ContainerResponseContext response) {
    final MultivaluedMap<String, Object> headers = response.getHeaders();
    headers.putSingle("Access-Control-Allow-Origin", "*");
    headers.putSingle("Access-Control-Expose-Headers", "Location");
    headers.putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    headers.putSingle("Access-Control-Allow-Headers",
                      "Content-Type, User-Agent, X-Requested-With, X-Requested-By, Cache-Control");
    headers.putSingle("Access-Control-Allow-Credentials", "true");
  }
}
