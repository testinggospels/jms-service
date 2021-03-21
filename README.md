# kafka-service
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

[Spring Boot](http://projects.spring.io/spring-boot/) Application for your Kafka testing needs.
## Requirements
For building and running the application you need:
- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

## Documentation
https://github.com/fauxauldrich/kafka-service/wiki
## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.fauxauldrich.kafkaservice.KafkaServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
```shell
mvn spring-boot:run
```

## Copyright
Released under the MIT License. See the [LICENSE](https://github.com/fauxauldrich/kafka-service/blob/main/LICENSE) file.
