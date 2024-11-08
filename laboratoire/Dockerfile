FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar laboratoire.jar

EXPOSE 8070

ENTRYPOINT ["java", "-jar", "/app/laboratoire.jar"]
