FROM openjdk:17-jdk-slim-buster

RUN apt-get update;
RUN apt-get install -y --no-install-recommends fontconfig libfreetype6;

WORKDIR /app

COPY target/baseproject-0.0.1-SNAPSHOT.jar /app/baseproject.jar

ENTRYPOINT ["java","-jar","baseproject.jar"]