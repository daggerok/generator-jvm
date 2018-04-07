package daggerok.ejb.impl;

import daggerok.ejb.api.HelloRepository;
import daggerok.ejb.api.HelloService;
import daggerok.entity.Hello;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;

import static java.util.stream.Collectors.joining;

@Stateless
public class HelloServiceBean implements HelloService {

  @Inject
  HelloRepository helloRepository;

  @Override
  public String sayHello(final String name) {

    final String content = "Hola, " + name + "!";
    final Hello hello = new Hello().setContent(content);

    return helloRepository.save(hello).getContent();
  }

  @Override
  public String getAllGreetings() {

    return Json.createObjectBuilder()
               .add("all", helloRepository.findAll()
                                          .stream()
                                          .map(hello -> Json.createObjectBuilder()
                                                            .add("id", hello.getId())
                                                            .add("content", hello.getContent())
                                                            .build()
                                                            .toString())
                                          .collect(joining(", ")))
               .build()
               .toString();
  }
}
