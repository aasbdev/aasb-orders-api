<<<<<<< HEAD
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
# Set environment variables for Maven build
ARG MAVEN_CLI_OPTS
ENV MAVEN_CLI_OPTS=${MAVEN_CLI_OPTS:-"-DskipTests"}

=======
FROM maven:3.8.5-openjdk-17 AS build
>>>>>>> 5b5949e59d30103faa20927943c8e6bac69a15f8
WORKDIR /build
COPY . .  

RUN mvn clean package ${MAVEN_CLI_OPTS}

FROM openjdk:17-jdk-slim 

# Set environment variables for Spring Boot
ARG SPRING_PROFILE
ENV SPRING_PROFILE=${SPRING_PROFILE:-"default"}

# Database connection variables
ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD

ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}

COPY --from=build ./build/target/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar", \
  "--spring.profiles.active=${SPRING_PROFILE}", \
  "--spring.datasource.url=${SPRING_DATASOURCE_URL}", \
  "--spring.datasource.username=${SPRING_DATASOURCE_USERNAME}", \
  "--spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}"]

