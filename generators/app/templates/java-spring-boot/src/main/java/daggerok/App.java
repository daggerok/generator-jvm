package daggerok;

import io.vavr.collection.HashMap;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RenderingResponse.create;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Log4j2
@Configuration
class WebfluxRoutesConfig {

  @Bean(name = "baseUrl")
  Function<ServerRequest, String> baseUrl() {
    return request -> {
      final URL url = Try.of(() -> request.uri().toURL())
                         .getOrElseThrow(() -> new RuntimeException("can't parse URL from request..."));
      return format("%s://%s", url.getProtocol(), url.getAuthority());
    };
  }

  @Bean
  HandlerFunction<RenderingResponse> indexHandler() {
    return request -> create("index").modelAttribute("message", "ololo troilolo!")
                                     .build();
  }

  @Bean
  HandlerFunction<ServerResponse> helloApiHandler() {
    return request -> ok().contentType(APPLICATION_JSON_UTF8)
                          .body(Mono.just(HashMap.of("hello", "world")
                                                 .toJavaMap()), Map.class);
  }

  @Bean
  HandlerFunction<ServerResponse> fallbackApiHandler(@Qualifier("baseUrl") final Function<ServerRequest, String> baseUrl) {
    return request -> ok().body(Mono.just(asList(
      format("GET /api   -> %s", baseUrl.apply(request)),
      format("GET /hello -> %s/api/hello", baseUrl.apply(request)),
      format("GET /**    -> %s/api/**", baseUrl.apply(request)))), List.class);
  }

  @Bean
  RouterFunction routes(final HandlerFunction<RenderingResponse> indexHandler,
                        final HandlerFunction<ServerResponse> helloApiHandler,
                        final HandlerFunction<ServerResponse> fallbackApiHandler) {

    resources("/**", new ClassPathResource("/public"));

    return
      nest(
        path("/"),
        nest(
          accept(TEXT_HTML),
          route(
            GET("/"),
            indexHandler
          )
        ).andOther(
          nest(
            accept(APPLICATION_JSON),
            nest(
              path("/api"),
              route(
                GET("/hello"),
                helloApiHandler
              ).andRoute(
                GET("/**"),
                fallbackApiHandler
              )
            )
          )
        )
      )
      ;
  }
}

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
