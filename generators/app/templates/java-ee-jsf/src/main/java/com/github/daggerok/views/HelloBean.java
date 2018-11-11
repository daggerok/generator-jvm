package com.github.daggerok.views;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@Getter
@RequestScoped
public class HelloBean {

  String helloText;

  @Setter
  String name;

  public void sayHello() {
    helloText = "Welcome, " + name + "!";
    name = null;
  }
}
