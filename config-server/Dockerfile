# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY target/*.jar config-server.jar

# Expose the port
EXPOSE 8888

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/config-server.jar"]
