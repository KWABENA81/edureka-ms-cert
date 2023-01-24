#   Dockerfile for oauth2 server
#
FROM openjdk:11

#   label image
LABEL Description="Oauth2 Server" Version="0.0.1"

ARG VERSION=0.0.1

VOLUME /tmp

ADD target/oauth2-${VERSION}-SNAPSHOT.jar app.jar

#
RUN sh -c 'touch /app.jar'

#   entrypoint
ENTRYPOINT ["java", "-jar", "/app.jar"]
