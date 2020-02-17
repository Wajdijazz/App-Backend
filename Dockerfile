FROM openjdk:8-jdk-alpine

ENV APP_NAME=followup-0.0.1-SNAPSHOT.jar
ENV JAR_FILE=target/${APP_NAME}
COPY ${JAR_FILE} followup.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/followup.jar"]
