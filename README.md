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

Fastest way to get started is by using the image available on [Docker Hub](https://hub.docker.com/r/shubhendumadhukar/kafka-service):

- Use the `docker-compose.yml` to bring up your container. (Modify `docker-compose.yml` to reflect the dir path for your truststore files under volumes and update environment variables accordingly)
- Or alternatively, to build image locally use Dockerfile provided.
  - Build with : `docker build -t kafka-service:${VERSION} .`
  - Run as a container using:
  ````shell
   docker run -d -env spring.kafka.bootstrap-servers=127.0.0.1:9092 -env kafkaservice.truststore.location=/app/certs/truststore.jks -env kafkaservice.truststore.password=Password@123 --name kafka-service -p 8080:8080 -v /dir/containing/truststore.jks/files:/app/certs kafka-service:${VERSION}```
  ````

**Run from your IDE**

- Execute the `main` method in the `com.fauxauldrich.kafkaservice.KafkaServiceApplication` class from your IDE.
- Or you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
  ```shell
  mvn spring-boot:run
  ```

**Run as a JAR**

- You can also download the JAR file from [Releases](https://github.com/fauxauldrich/kafka-service/releases) and run it locally:
  ```shell
  java -Dspring.kafka.bootstrap-servers=127.0.0.1:9092 -Dkafkaservice.truststore.location=/home/truststore.jks -Dkafkaservice.truststore.password=Password -jar kafka-service-${VERSION}.jar
  ```
- Or, you can build locally and run the jar file

  ```shell
  ./mvnw clean install package -f pom.xml

  java -Dspring.kafka.bootstrap-servers=127.0.0.1:9092 -Dkafkaservice.truststore.location=/home/truststore.jks -Dkafkaservice.truststore.password=Password -jar target/kafka-service-${VERSION}.jar
  ```

## Copyright

Released under the MIT License. See the [LICENSE](https://github.com/fauxauldrich/kafka-service/blob/main/LICENSE) file.
