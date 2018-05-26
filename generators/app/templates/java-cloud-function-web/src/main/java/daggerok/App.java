package daggerok;

import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
public class App {

  @Bean
  Function<Map<String, String>, Map<String, String>> routes() {
    return map -> HashMap.ofAll(map)
        .map((s, s2) -> Tuple.of(s, s2.toUpperCase()))
        .toJavaMap();
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
