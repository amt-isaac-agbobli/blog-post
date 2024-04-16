# Stage 1: Build the application
# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the Docker image
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /workspace/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar","/app/app.jar"]