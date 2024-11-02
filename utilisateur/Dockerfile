FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar utilisateurs.jar

EXPOSE 8070

ENTRYPOINT ["java", "-jar", "/app/utilisateurs.jar"]
