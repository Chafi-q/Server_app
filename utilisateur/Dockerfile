FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar utilisateur.jar

EXPOSE 8070

ENTRYPOINT ["java", "-jar", "/app/utilisateur.jar"]
