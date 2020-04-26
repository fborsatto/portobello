FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} portobello-limit.jar
ENTRYPOINT ["java","-jar","/portobello-limit.jar"]