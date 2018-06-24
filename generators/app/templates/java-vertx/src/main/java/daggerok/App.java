package daggerok;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.HashMap;
import io.vavr.control.Try;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class App {

  static final int port = 8080;
  static final ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) {

    final HttpServer server = Vertx.vertx().createHttpServer();

    server.requestHandler(event -> {

      log.info("hendling request");

      final Map<String, String> o = HashMap.of("hello", "world",
                                               "ololo", "trololo")
                                           .toJavaMap();
      final String json = Try.of(() -> mapper.writeValueAsString(o))
                             .getOrElseGet(throwable -> "{}");
      event.response()
           .putHeader("Content-Type", "application/json")
           .end(json);
    });

    server.listen(port, event -> log.info("listening {} port.", port));
  }
}
