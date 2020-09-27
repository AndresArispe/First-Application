FROM openjdk:8-jdk-alpine

MAINTAINER <andres.arispe.medina@gmail.com>

ENV APP_PORT=8090

ENV DB_URL=jdbc:postgresql://postgres:5432/order
ENV DB_USER=postgres
ENV DB_PASSWORD=godis1first

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar

EXPOSE ${APP_PORT}
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]