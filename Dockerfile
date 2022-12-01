FROM openjdk:20-slim-buster
VOLUME /tmp
ADD war/study02-0.0.1-SNAPSHOT.war app.jar
ENV JAVA_OPTS=""
EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]