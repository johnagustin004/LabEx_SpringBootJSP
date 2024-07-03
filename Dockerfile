# Use a base image with Java 21 runtime
FROM openjdk:21-slim

# Set the working directory inside the container
WORKDIR /app

# Add Maven or Gradle wrapper script to run build
COPY mvnw ./
COPY .mvn ./.mvn
COPY pom.xml ./
COPY src ./src

# Package the application
RUN ./mvnw clean package -DskipTests

# Copy the packaged jar file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]