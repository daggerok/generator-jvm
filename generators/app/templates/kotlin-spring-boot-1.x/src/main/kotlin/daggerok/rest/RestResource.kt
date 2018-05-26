package daggerok.rest

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = [APPLICATION_JSON_UTF8_VALUE])
class RestResource {

  @GetMapping(path = ["", "/", "/{name}"])
  fun index(@PathVariable(required = false) name: String? = null) =
      mapOf("hello" to "${name ?: "world"}!")
}
