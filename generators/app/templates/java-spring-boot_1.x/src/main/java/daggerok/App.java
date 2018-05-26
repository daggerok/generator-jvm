package daggerok;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Slf4j
@RestControllerAdvice
class RestResourceErrorHandler {

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(Throwable.class)
  public ResponseEntity fallback(final HttpServletRequest request, final Throwable e) {
    final String error = Try.of(e::getLocalizedMessage).getOrElse(() -> "unknown error");
    log.error("exception handling: {}", error);
    return ResponseEntity.badRequest().body(singletonMap("error", error));
  }
}

@RestController
@RequestMapping(
    produces = APPLICATION_JSON_UTF8_VALUE
)
class RestResource {

  @GetMapping({"", "/", "/{name}"})
  public ResponseEntity api(@PathVariable final Optional<String> name) {
    final String result = name.orElse("world");
    return ResponseEntity.ok(singletonMap("hello", result));
  }
}

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
