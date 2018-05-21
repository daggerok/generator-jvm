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


Then generate your new project:

```bash
yo jvm
# enter projectDirectory or just press enter (default: app)
# chose projectType (default: java)
cd $projectDirectory
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

java-ee-faces project:

 * javaee 8.0 project
 * JSF using mojarrra 2.2.16 faces implementation
 * lombok (slf4j + logback logging)
 * support maven
 * support gradle
 * docker / docker-compose support (Glassfish 5.0 / JBOSS EAP 7.1)

java-ee CDI full multi project:

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

java-ee EJB full multi project:

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

kotlin java-ee project:

 * javaee 8.0 project
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support kotlin
 * support maven
 * support gradle
 * support testing with junit 4 / 5
 * docker / docker-compose support (JBOSS EAP 7.1)

kotlin fat project

 * java 8 based project
 * support kotlin
 * support fatjar
 * support maven
 * support gradle
 * slf4j + logback logging
 * support application scripts when using gradle
 * docker / docker-compose support

spring cloud function web project

 * java 8 based spring-cloud-function-web project
 * spring-boot 2 / spring framework 5
 * support fatjar
 * support executable bash jar
 * support kotlin
 * support maven
 * support gradle
 * docker / docker-compose support

spring-boot 1 / spring framework 4 project

 * java 8 based project
 * spring-boot 1 / spring framework 4
 * support fatjar
 * support executable bash jar
 * support kotlin
 * support maven
 * support gradle
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

kotlin parent multi project:

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

spring-boot 2 / spring framework 5 project

 * java 8 based project
 * spring-boot 2 / spring framework 5
 * support fatjar
 * support executable bash jar
 * support kotlin
 * support maven
 * support gradle
 * vavr (javaslang)
 * lombok (slf4j + logback logging)
 * support testing with junit 4 / 5
 * docker / docker-compose support

scala akka-persistence gradle project

 * scala 2.12 based project
 * akka-persistence
 * lombok (slf4j + logback logging)
 * support gradle and application executable scripts
 * support testing using junit 4, scalactic, scalatest, specs2
 * docker / docker-compose support

java-akka project

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

java-ee (micro-profile) project:

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

scala project:

 * scala 2.12 based project
 * lombok (slf4j + logback logging)
 * support fatjar
 * support maven
 * support gradle
 * support application executable scripts when using gradle
 * support testing using junit 4, scalactic, scalatest, specs2
 * docker / docker-compose support

java-ee project:

 * java 8 based project
 * javaee 8.0
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support maven
 * support gradle
 * support testing with junit 4 / 5
 * docker / docker-compose support (JBOSS EAP 7)

java parent multi project:

 * java 8 based parent multi project
 * vavr (javaslang)
 * support maven
 * support gradle
 * docker / docker-compose support

scala_2.11 project:

 * scala 2.11 based project
 * lombok (slf4j + logback logging)
 * support fatjar
 * support maven
 * support gradle
 * support application scripts (gradle shadow plugin)
 * support testing using junit 4, scalactic, scalatest, specs2
 * docker / docker-compose support

java project:

 * java 8 based project
 * lombok (slf4j + logback logging)
 * vavr (javaslang)
 * support fatjar
 * support maven
 * support gradle
 * support application scripts when using gradle
 * support testing with junit 4 / 5
 * docker / docker-compose support

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
