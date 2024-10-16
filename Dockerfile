FROM openjdk:17
ARG JAR_FILE=/build/libs/*.jar
EXPOSE 8080
COPY build/libs/SpringJWT-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]