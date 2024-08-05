FROM maven:3.8.7-eclipse-temurin-19 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
COPY --form=build /target/demo-0.0.1-SNAPSHOT.jar JJKdle.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","JJKdle.jar"]

