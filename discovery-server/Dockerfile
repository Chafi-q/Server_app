FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar discovery-server.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/discovery-server.jar"]
