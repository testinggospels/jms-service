FROM openjdk:11.0.10-jdk-oraclelinux7
RUN mkdir /app
RUN mkdir /app/certs
WORKDIR /app
COPY target/* /app
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "kafka-service-0.0.1.jar"]
