FROM openjdk:11-alpine
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "-Dspring.profiles.active=production"]
# changeset backend test19
