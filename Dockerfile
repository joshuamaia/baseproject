FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY target/baseproject-0.0.1-SNAPSHOT.jar /app/baseproject.jar

ENTRYPOINT ["java","-jar","baseproject.jar"]