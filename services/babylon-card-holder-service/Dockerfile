# Stage 1: Build the web
FROM maven:3.9-eclipse-temurin-20 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Stage 2: Run the web
FROM eclipse-temurin:20-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
