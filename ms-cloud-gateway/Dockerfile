FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG RABBITMQ_SERVER=lofimsrabbitmq
ARG KEYCLOARK_SERVER=localhost
ARG KEYCLOARK_SERVER=8080

ENTRYPOINT java -jar -Dspring.profiles.active=production app.jar