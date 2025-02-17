FROM maven:3.8.7-eclipse-temurin-17 AS builder

WORKDIR /app

COPY . /app
COPY src/main/resources /app/src/main/resources

RUN mvn clean package -DskipTests


FROM openjdk:17-jdk

WORKDIR /app

COPY target/TaskManagerForTeam-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
