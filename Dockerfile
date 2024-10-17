FROM openjdk:21-jdk-slim

ARG JAR_FILE=target/jobhub-api-0.0.1.jar

COPY ${JAR_FILE} jobhub-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "jobhub-api.jar"]