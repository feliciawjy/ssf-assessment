FROM openjdk:21-jdk-bullseye AS builder

WORKDIR /app

COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn
COPY src src
COPY movies.json .


RUN chmod a+x mvnw
RUN ./mvnw package -Dmaven.test.skip=true


FROM openjdk:21-jdk-bullseye

WORKDIR /app_run

COPY --from=builder /app/target/ibf-b4-ssf-assessment-0.0.1-SNAPSHOT.jar app.jar
COPY --from=builder /app/movies.json /app_run/movies.json

##run
ENV PORT=8080 


EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar