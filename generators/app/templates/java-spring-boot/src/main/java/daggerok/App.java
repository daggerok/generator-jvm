package daggerok;

import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
class WebfluxRoutesConfig {

  @SneakyThrows
  @Bean(name = "baseUrl")
  String baseUrl(final Environment env) {

    final String hostAddressValue = InetAddress.getLocalHost().getHostAddress();
    final String hostAddress = null == hostAddressValue ? "127.0.0.1" : hostAddressValue;
    final String portNumber = env.getProperty("server.port", "8080");
    final Integer port = Try.of(() -> Integer.parseInt(portNumber)).getOrElseGet(throwable -> 8080);

    return format("http://%s:%d", hostAddress, port);
  }

  @Bean
  HandlerFunction<ServerResponse> helloWorldHandler() {
    return request ->
        ok().contentType(APPLICATION_JSON_UTF8)
            .body(Mono.just(singletonMap("hello", "world")), Map.class);
  }

  @Bean
  HandlerFunction<ServerResponse> propsHandler() {
    return request -> ok().body(Flux.just("props..."), String.class);
  }

  @Bean
  HandlerFunction<ServerResponse> fallbackHandler(@Qualifier("baseUrl") final String baseUrl) {
    return request ->
        ok().body(Mono.just(asList(
            format("GET   api -> %s/api", baseUrl),
            format("GET props -> %s/api/props", baseUrl),
            format("GET     * -> %s/**", baseUrl))), List.class);
  }


  @Bean
  RouterFunction routes(final HandlerFunction<ServerResponse> fallbackHandler) {
    return
        nest(
            path("/"),
            nest(
                accept(APPLICATION_JSON),
                route(
                    GET("/api"),
                    helloWorldHandler()
                ).andNest(
                    path("/api"),
                    route(
                        GET("/props"),
                        propsHandler())
                )
            )
        ).andOther(
            route(
                GET("/**"),
                fallbackHandler
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
