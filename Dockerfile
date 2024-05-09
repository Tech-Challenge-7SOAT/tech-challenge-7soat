# kotlin image
FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install any needed packages specified in pom.xml
RUN apk add --no-cache maven

# Build the project
RUN mvn clean install

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/fastfood-api-0.0.1-SNAPSHOT.jar"]
