# generator-jvm [![NPM version][npm-image]][npm-url] [![Build Status][travis-image]][travis-url] [![Dependency Status][daviddm-image]][daviddm-url]
> Generate JVM (java, kotlin, scala) project with gradle / maven build systems and docker / docker-compose for rapid development

## See also

I aslo have a lot of jvm starters [here](https://github.com/daggerok/main-starter), and not sure that will move all of them inside this generator

## Installation

First, install [Yeoman](http://yeoman.io) and generator-jvm using [npm](https://www.npmjs.com/) (we assume you have pre-installed [node.js](https://nodejs.org/)).

```bash
npm install -g yo
npm install -g generator-jvm
```

## npm audit

It's not recommended, but if you really need, you can install npm packages without audit by using:

```bash
npm install --no-audit
```

## Usage

Then generate your new project using command line options (`name` and `type`):

```bash
yo jvm --name my-awesom-java-project --type java
yo jvm -n spring-boot-kotlin-project -t kotlin-spring-boot
cd my-awesom-java-project
```

Then generate your new project using prompt:

```bash
yo jvm
# enter projectDirectory or just press enter (default: app)
# chose projectType (default: java)
cd app
```

using gradle

```bash
idea build.gradle
bash gradlew
```

using maven

```bash
idea pom.xml
bash mvnw
```

docker-compose

```bash
# gradle
bash gradlew composeUp
# maven
bash mvnw docker-compose:up
```

## What's inside?

___java-ee (micro-profile) project___

 * java 8 based project
 * javaee 7.0 / 8.0 using thorntail (aka wildfly-swarm) micro-profile
 * support fatjar
 * support executable bash jar
 * support kotlin
 * support maven build system
 * support gradle build system (only swarm projects)
 * vavr (aka javaslang), lombok
 * support testing with junit 4 / 5
 * docker / docker-compose support

type options:

- `java-thorntail`
- `java-wildfly-swarm`
- `kotlin-thorntail`
- `kotlin-wildfly-swarm`

___java-payara-micro___

 * java 8 based project
 * Java / JavaEE 8
 * lombok (slf4j + logback logging)
 * support fat (uber) jar
 * support maven build system
 * support gradle build system
 * support testing with junit 5
 * docker / docker-compose support

type options:

- `java-payara-micro`

___akka-actor project___

 * java 8 based project
 * scala 2.12
 * akka-actor 2.5.14
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support fatjar
 * support sbt build system
 * support maven build system
 * support gradle build system
 * support application scripts when using gradle
 * support testing with junit 4 / 5
 * docker / docker-compose support

type options:

- `java-akka-actor`
- `scala-gradle-akka-actor`
- `scala-sbt-akka-actor`

___akka-persistence project___

 * java 8 based project
 * scala 2.12
 * akka-actor 2.5.14 / akka-persistence
 * support fatjar
 * support gradle build system
 * docker / docker-compose support

type options:

- `scala-gradle-akka-persistence`

___java-ee thymeleaf project___

 * Java EE 8 based web project
 * Thymeleaf
 * JAX-RX
 * support JBOSS EAP 7.1 (docker)
 * support maven build system
 * support gradle build system
 * docker / docker-compose support

type options:

- `java-ee-thymeleaf`

___vertx project___

 * java 8 based Vert.x web project
 * support fatjar
 * support kotlin 1.3.11
 * support maven build system
 * support gradle build system
 * docker / docker-compose support

type options:

- `java-vertx`
- `kotlin-vertx`

___spring cloud function web project___

 * java 8 based spring-cloud-function-web project
 * spring-boot 2 / spring framework 5
 * support fatjar
 * support executable bash jar
 * support kotlin 1.3.11
 * support maven build system
 * support gradle build system
 * docker / docker-compose support

type options:

- `java-spring-cloud-function-web`
- `kotlin-spring-cloud-function-web`

___spring-boot 2 / spring framework 5 project___

 * java 8 based project
 * spring-boot 2 / spring framework 5
 * support fatjar
 * support executable bash jar
 * support kotlin 1.3.11
 * support maven build system
 * support gradle build system
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

type options:

- `java-spring-boot`
- `kotlin-spring-boot`
- `java-spring-boot-2.0.x`
- `kotlin-spring-boot-2.0.x`

___spring-boot 1 / spring framework 4 project___

 * java 8 based project
 * spring-boot 1 / spring framework 4
 * support fatjar
 * support executable bash jar
 * support kotlin 1.2.71
 * support maven build system
 * support gradle build system
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

type options:

- `java-spring-boot-1.x`
- `kotlin-spring-boot-1.x`

___java-ee-faces project___

 * javaee 8.0 project
 * JSF using mojarrra 2.2.16 faces implementation
 * lombok (slf4j + logback logging)
 * support maven build system
 * support gradle build system
 * docker / docker-compose support (Glassfish 5.0 / JBOSS EAP 7.1)

___java-ee EAR multi project___

 * javaee 8.0 project
 * EAR with EJB + CDI configured
 * EAR without CDI at all, but could be easily added (`java-ee-cdi-multi-project`)
 * EJB3 (business services module: ejb-services)
 * EJB3 (JPA repositories module: ejb-data with embedded H2database for simplicity)
 * JAX-RS REST API
 * WEB/HTML5 static content
 * Servlet and JSP with EJB usage
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support maven build system
 * support gradle build system
 * docker / docker-compose support (JBOSS EAP 7.1)

type options:

- `java-ee-cdi-full-multi-project`
- `java-ee-ejb-full-multi-project`

___java-ee project___

 * java 8 based project
 * javaee 8.0
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support kotlin 1.3.11
 * support maven build system
 * support gradle build system
 * support testing with junit 4 / 5
 * docker / docker-compose support (JBOSS EAP 7)

type options:

- `java-ee`
- `kotlin-ee`

___parent multi project___

 * java 8 based parent multi project
 * support fatjar
 * support kotlin 1.3.11
 * support executable bash jar
 * support maven build system
 * support gradle build system
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

type options:

- `java-parent-multi-project`
- `kotlin-parent-multi-project`

___simple fat project___

 * java 8 based project
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support fatjar
 * support scala 2.12 / 2.11
 * support maven build system
 * support gradle build system
 * docker / docker-compose support
 * support application scripts when using gradle
 * support application executable scripts when using gradle
 * support testing using junit 4, scalactic, scalatest, specs2

type options:

- `java`
- `kotlin`
- `scala`
- `scala-2.11`
- `scala-sbt`

## Also inside all projects available

- FindBugs maven / gradle plugins functionality
  ```./gradlew check```
  ```./mvnw verify site```
- Ascii documentation (asciidoctor maven / gradle plugins functionality)
  ```./gradlew asciidoctor```
- Artifact deployment (local maven repositories)
  ```./gradlew publish``` 
  ```./mvnw deploy``` 

## Getting To Know Yeoman

 * Yeoman has a heart of gold.
 * Yeoman is a person with feelings and opinions, but is very easy to work with.
 * Yeoman can be too opinionated at times but is easily convinced not to be.
 * Feel free to [learn more about Yeoman](http://yeoman.io/).

## License

MIT © [Maksim Kostromin](https://github.com/daggerok)

[npm-image]: https://badge.fury.io/js/generator-jvm.svg
[npm-url]: https://npmjs.org/package/generator-jvm
[travis-image]: https://travis-ci.org/daggerok/generator-jvm.svg?branch=master
[travis-url]: https://travis-ci.org/daggerok/generator-jvm
[daviddm-image]: https://david-dm.org/daggerok/generator-jvm.svg?theme=shields.io
[daviddm-url]: https://david-dm.org/daggerok/generator-jvm
[coveralls-image]: https://coveralls.io/repos/daggerok/generator-jvm/badge.svg
[coveralls-url]: https://coveralls.io/r/daggerok/generator-jvm
