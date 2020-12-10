# Clocker



## Install
* Run
```
mvn install
java -jar target/*.jar
```




### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#production-ready)


### Data test
```
POST http://localhost:8080/login HTTP/1.1
Content-Type: application/json

{
    "username": "root",
    "password": "root"
}


###

POST http://localhost:8080/employee HTTP/1.1
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290Iiwicm9sZXMiOlsiUk9MRV9hZG1pbiIsIlJPTEVfdXNlciJdLCJleHAiOjE2MDg0NzMzMjl9.cfblw5PIB2qLDfH9r6QihhZDYoJdmfYvKvEeL51IQWFJnPwGriTGZQ-efHRuxVTVsg2E9vF5lRTQmntflJW59Q
Content-Type: application/json

{
    "name": "Jose María", 
    "surname": "Soler"
}

###

GET http://localhost:8080/employee/list HTTP/1.1
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290Iiwicm9sZXMiOlsiUk9MRV9hZG1pbiIsIlJPTEVfdXNlciJdLCJleHAiOjE2MDg0NzMzMjl9.cfblw5PIB2qLDfH9r6QihhZDYoJdmfYvKvEeL51IQWFJnPwGriTGZQ-efHRuxVTVsg2E9vF5lRTQmntflJW59Q
Content-Type: application/json

### Añadir un registro horario con hora de entrada

POST http://localhost:8080/record HTTP/1.1
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290Iiwicm9sZXMiOlsiUk9MRV9hZG1pbiIsIlJPTEVfdXNlciJdLCJleHAiOjE2MDg0NzMzMjl9.cfblw5PIB2qLDfH9r6QihhZDYoJdmfYvKvEeL51IQWFJnPwGriTGZQ-efHRuxVTVsg2E9vF5lRTQmntflJW59Q
Content-Type: application/json

{
  "employee": {
    "id": 1
  },
  "startDate": "2020-12-10T14:00:00.000Z"
}

### Actualizar un registro horario con hora de salida

PUT http://localhost:8080/record/1 HTTP/1.1
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290Iiwicm9sZXMiOlsiUk9MRV9hZG1pbiIsIlJPTEVfdXNlciJdLCJleHAiOjE2MDg0NzMzMjl9.cfblw5PIB2qLDfH9r6QihhZDYoJdmfYvKvEeL51IQWFJnPwGriTGZQ-efHRuxVTVsg2E9vF5lRTQmntflJW59Q
Content-Type: application/json

{
  "employee": {
    "id": 1
  },
  "startDate": "2020-12-10T14:00:00.000Z",
  "endDate": "2020-12-10T20:00:00.000Z"
}
```