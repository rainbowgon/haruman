FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dserver.port=8081", "-Dspring.profiles.active=develop","/app.jar"]