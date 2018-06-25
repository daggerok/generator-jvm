package daggerok

import com.fasterxml.jackson.databind.ObjectMapper
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

val vertx: Vertx by lazy { Vertx.vertx() }
val router: Router by lazy { Router.router(vertx) }
val mapper: ObjectMapper by lazy { ObjectMapper() }

fun Any.toJson() = mapper.writeValueAsString(this)!!

fun RoutingContext.json(payload: String) = this.response()
  .putHeader("Content-Type", "application/json")
  .end(payload)

val fallbackHandler: (RoutingContext) -> Unit = {
  it.json(
    mapOf(
      "hello path" to "GET /api/{name}",
      "hello query" to "GET /api?name={name}"
    ).toJson()
  )
}

val greetingHandler: (RoutingContext) -> Unit = { event ->

  val param = event.request().params()["name"] ?: ""
  val query = event.queryParams()["name"] ?: ""
  var name = "my friend"

  if (param.isNotEmpty()) name = param
  if (query.isNotEmpty()) name = query

  val payload = mapOf("message" to "Hello, ${name.capitalize()}!")

  event.json(payload.toJson())
}

fun main(args: Array<String>) {

  router.get("/api/:name?").handler(greetingHandler)

  router.get("/*").handler(fallbackHandler)

  vertx.createHttpServer()
    .requestHandler(router::accept)
    .listen(8080)
}
