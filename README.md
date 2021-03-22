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

**Run as a Docker container**
Fastest way to get started is by using the image available on hub.docker.com:

- Use the `docker-compose.yml` to bring up your container. (Modify `docker-compose.yml` to reflect the dir path for your truststore files)
- Or alternatively, to build image locally use Dockerfile provided.
- Build with : `docker build -t kafka-service:0.0.1 .`
- Run as a container using: `docker run -d --name kafka-service -p 8080:8080 -v /dir/containing/truststore.jks/files:/app/certs kafka-service:0.0.1`

There are several other ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.fauxauldrich.kafkaservice.KafkaServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Copyright

Released under the MIT License. See the [LICENSE](https://github.com/fauxauldrich/kafka-service/blob/main/LICENSE) file.

