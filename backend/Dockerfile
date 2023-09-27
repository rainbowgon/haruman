FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
ARG PORT="$target_port"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dserver.port=${PORT}", "-Dspring.profiles.active=develop","/app.jar"]