# Use a lightweight Alpine OpenJDK runtime as a base image
FROM openjdk:17-alpine

# Copy the packaged JAR file from the build stage
COPY target/*.jar app.jar

# Expose the port
EXPOSE 8036

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
