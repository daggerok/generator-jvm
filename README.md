# generator-jvm [![NPM version][npm-image]][npm-url] [![Build Status][travis-image]][travis-url] [![Dependency Status][daviddm-image]][daviddm-url]
> Generate JVM (java, kotlin, scala) project with gradle / maven build systems and docker / docker-compose for rapid development

## Installation

First, install [Yeoman](http://yeoman.io) and generator-jvm using [npm](https://www.npmjs.com/) (we assume you have pre-installed [node.js](https://nodejs.org/)).

```bash
npm install -g yo
npm install -g generator-jvm
```

## npm audit

I'm not recommend you, but you can stop npm audit on install by using

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
bash mvnw com.dkanejs.maven.plugins:docker-compose-maven-plugin:1.0.1:up
```

## What's inside?

___spring cloud function web project___

 * java 8 based spring-cloud-function-web project
 * spring-boot 2 / spring framework 5
 * support fatjar
 * support executable bash jar
 * support kotlin or java
 * support maven
 * support gradle
 * docker / docker-compose support

type options:

- `java-spring-cloud-function-web`
- `kotlin-spring-cloud-function-web`

___spring-boot 2 / spring framework 5 project___

 * java 8 based project
 * spring-boot 2 / spring framework 5
 * support fatjar
 * support executable bash jar
 * support kotlin or java
 * support maven
 * support gradle
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

type options:

- `java-spring-boot`
- `kotlin-spring-boot`

___spring-boot 1 / spring framework 4 project___

 * java 8 based project
 * spring-boot 1 / spring framework 4
 * support fatjar
 * support executable bash jar
 * support kotlin or java
 * support maven
 * support gradle
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

type options:

- `java-spring-boot-1.x`
- `kotlin-spring-boot-1.x`

___kotlin parent multi project___

 * java 8 based parent multi project
 * support kotlin
 * support fatjar
 * support executable bash jar
 * support kotlin
 * support maven
 * support gradle
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

___java-ee-faces project___

 * javaee 8.0 project
 * JSF using mojarrra 2.2.16 faces implementation
 * lombok (slf4j + logback logging)
 * support maven
 * support gradle
 * docker / docker-compose support (Glassfish 5.0 / JBOSS EAP 7.1)

___java-ee CDI full multi project___

 * javaee 8.0 project
 * EAR with EJB + CDI configured
 * EJB3 (business services module: ejb-services)
 * EJB3 (JPA repositories module: ejb-data with embedded H2database for simplicity)
 * JAX-RS REST API
 * WEB/HTML5 static content
 * Servlet and JSP with EJB usage
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support maven
 * support gradle
 * docker / docker-compose support (JBOSS EAP 7.1)

___java-ee EJB full multi project___

 * javaee 8.0 project
 * EAR without CDI at all, but could be easily added (see java-ee-cdi-multi-project)
 * EJB3 (business services module: ejb-services)
 * EJB3 (JPA repositories module: ejb-data with embedded H2database for simplicity)
 * JAX-RS REST API
 * WEB/HTML5 static content
 * Servlet and JSP with EJB usage
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support maven
 * support gradle
 * docker / docker-compose support (JBOSS EAP 7.1)

___kotlin java-ee project___

 * javaee 8.0 project
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support kotlin
 * support maven
 * support gradle
 * support testing with junit 4 / 5
 * docker / docker-compose support (JBOSS EAP 7.1)

___kotlin fat project___

 * java 8 based project
 * support kotlin
 * support fatjar
 * support maven
 * support gradle
 * slf4j + logback logging
 * support application scripts when using gradle
 * docker / docker-compose support

___scala akka-persistence gradle project___

 * scala 2.12 based project
 * akka-persistence
 * lombok (slf4j + logback logging)
 * support gradle and application executable scripts
 * support testing using junit 4, scalactic, scalatest, specs2
 * docker / docker-compose support

___java-akka project___

 * java 8 based project
 * akka-actor
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support fatjar
 * support maven
 * support gradle
 * support application scripts when using gradle
 * support testing with junit 4 / 5
 * docker / docker-compose support

___java-ee (micro-profile) project___

 * java 8 based project
 * javaee 8.0 using wildfly-swarm micro-profile
 * support fatjar
 * support executable bash jar
 * support kotlin
 * support maven
 * support gradle
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

___scala project___

 * scala 2.12 based project
 * lombok (slf4j + logback logging)
 * support fatjar
 * support maven
 * support gradle
 * support application executable scripts when using gradle
 * support testing using junit 4, scalactic, scalatest, specs2
 * docker / docker-compose support

___java-ee project___

 * java 8 based project
 * javaee 8.0
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support maven
 * support gradle
 * support testing with junit 4 / 5
 * docker / docker-compose support (JBOSS EAP 7)

___java parent multi project___

 * java 8 based parent multi project
 * vavr (javaslang)
 * support maven
 * support gradle
 * docker / docker-compose support

___scala_2.11 project___

 * scala 2.11 based project
 * lombok (slf4j + logback logging)
 * support fatjar
 * support maven
 * support gradle
 * support application scripts (gradle shadow plugin)
 * support testing using junit 4, scalactic, scalatest, specs2
 * docker / docker-compose support

___java project___

 * java 8 based project
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support fatjar
 * support maven
 * support gradle
 * support application scripts when using gradle
 * support testing with junit 4 / 5
 * docker / docker-compose support

## Also inside all projects available

- FindBugs maven / gradle plugins functionality
  ```./gradlew check```
  ```./mvnw verify site```
- Ascii documentation (asciidoctor gradle plugin)
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
