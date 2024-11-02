FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar gateway.jar

EXPOSE 8222

ENTRYPOINT ["java", "-jar", "/app/gateway.jar"]
