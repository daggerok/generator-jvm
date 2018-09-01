package daggerok

import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.toMono
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class App {

  @Bean fun routes() = router {
    ("/").nest {
      contentType(APPLICATION_JSON_UTF8)
      GET("/**") {
        ok().body(
          mapOf("hello" to "world").toMono()
        )
      }
    }
  }
}

fun main(args: Array<String>) {
  runApplication<App>(*args)
}
