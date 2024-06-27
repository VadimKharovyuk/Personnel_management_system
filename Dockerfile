# Use official OpenJDK image as a base image
FROM openjdk:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/Personnel_management_system-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your application runs on
EXPOSE 2024

# Command to run the application when the container starts
CMD ["java", "-jar", "/app/app.jar"]
