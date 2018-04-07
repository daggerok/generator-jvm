package daggerok.app

import javax.enterprise.context.ApplicationScoped
import javax.json.Json
import javax.ws.rs.core.UriInfo

fun UriInfo.to(resourceClass: Class<*>, method: String) =
    this.baseUriBuilder
        .path(resourceClass)
        .path(resourceClass, method)
        .build()
        .toString()

@ApplicationScoped
class JsonService {

  fun jsongify(uriInfo: UriInfo, javaClass: Class<*>, methodName: String) =
      Json.createObjectBuilder()
          .add("_self", uriInfo.to(javaClass, methodName))
}
