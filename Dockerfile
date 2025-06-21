## Use an official OpenJDK runtime as base image
#FROM openjdk:17-jdk-slim
#
## Set the working directory
#WORKDIR /app
#
## Copy the built JAR file into the container
#COPY target/*.jar app.jar
#
## Expose the port your app uses (change if different)
#EXPOSE 8081
#
## Run the JAR file
#ENTRYPOINT ["java", "-jar", "app.jar"]
