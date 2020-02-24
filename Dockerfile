FROM openjdk:8-jdk-alpine

ENV APP_NAME=followup*.jar
ENV JAR_FILE=target/${APP_NAME}

COPY ${JAR_FILE} followup.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/followup.jar"]
