# Clocker

[![Build Status](https://travis-ci.org/txuselo/clocker.svg?branch=develop)](https://travis-ci.org/github/txuselo/clocker)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

## Requirements

For building and running the application you need:

- [JDK 1.11](https://jdk.java.net/11/)
- [Maven 3](https://maven.apache.org)


## Install
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.redworks.clocker.ClockerApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Last option is running in traditional mode in your JVM. For this you must build and run with this commands:

```shell
mvn install
java -jar target/*.jar
```

## Try in dev mode with a test dataset
* Access h2 embed database [h2-console](http://localhost:8080/h2-console):
    - Driver Class: `org.h2.Driver`
    - JDBC URL: `jdbc:h2:mem:clocker`
    - User Name: `sa`
    - Password: Leave blank

* Access [swagger](http://localhost:8080/swagger-ui/index.html):
    - Need to authorize request with a valid Bearer JWT
    - Access `/login` endpoint with a valid user to get JWT

### Test Requests for [vscode extension](https://marketplace.visualstudio.com/items?itemName=humao.rest-client)
* Paste this in a `test.http` file
```
# Login for get Bearer token
POST http://localhost:8080/login HTTP/1.1
Content-Type: application/json

{
    "username": "root",
    "password": "root"
}
```
* Next requests needs a bearer token header from previous request
```
### Adding employee

POST http://localhost:8080/employee HTTP/1.1
Authorization: Bearer 
Content-Type: application/json

{
    "name": "John", 
    "surname": "Doe"
}

### Retrieve employee list

GET http://localhost:8080/employee/list HTTP/1.1
Authorization: Bearer 
Content-Type: application/json

### Adding time record with entry date and time

POST http://localhost:8080/record HTTP/1.1
Authorization: Bearer 
Content-Type: application/json

{
  "employee": {
    "id": 1
  },
  "startDate": "2020-12-10T14:00:00.000Z"
}

### Updating time record with exit date and time

PUT http://localhost:8080/record/1 HTTP/1.1
Authorization: Bearer 
Content-Type: application/json

{
  "employee": {
    "id": 1
  },
  "startDate": "2020-12-10T14:00:00.000Z",
  "endDate": "2020-12-10T20:00:00.000Z"
}
```

## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#production-ready)


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/txuselo/clocker/blob/master/LICENSE) file.
