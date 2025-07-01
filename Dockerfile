FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

FROM openjdk:17-jdk

COPY . .

RUN apt-get update && apt-get install -y maven
RUN mvn clean install


COPY --from=build /target/todolist-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]