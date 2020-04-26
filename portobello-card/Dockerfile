FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} portobello-card.jar
ENTRYPOINT ["java","-jar","/portobello-card.jar"]