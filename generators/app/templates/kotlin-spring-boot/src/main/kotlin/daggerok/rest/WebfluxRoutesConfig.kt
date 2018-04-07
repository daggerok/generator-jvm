package daggerok.rest

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Configuration
class WebfluxRoutesConfig {

  @Bean
  fun routes() = router {
    ("/").nest {
      contentType(MediaType.APPLICATION_JSON_UTF8)
      GET("/**") {
        val map = mapOf("hello" to "world")
        ok().body(
            Mono.just(map), map.javaClass
        )
      }
    }
  }
}
