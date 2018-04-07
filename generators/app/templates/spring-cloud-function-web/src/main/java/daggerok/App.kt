package daggerok

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.function.Function

@SpringBootApplication
class App {

  @Bean
  fun up(): Function<Map<String, String>, Map<String, String>> =
      Function { map -> map.mapValues { it.value.toUpperCase() } }
}

fun main(args: Array<String>) {
  runApplication<App>(*args)
}
