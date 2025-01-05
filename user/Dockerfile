# Build stage
FROM maven:3.9.9-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-jdk-slim
WORKDIR /app
    COPY --from=build /app/target/*.jar user.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "user.jar"]