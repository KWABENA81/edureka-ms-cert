#   Dockerfile for oauth2 server
#
FROM openjdk:11

ENV JAVA_VERSION=11.0.16

ENV JAVA_HOME=/usr/local/openjdk-11

#   label image
LABEL Description="Oauth2 Server" Version="0.0.1"

ARG VERSION=0.0.1

VOLUME /tmp

ADD target/oauth2-${VERSION}-SNAPSHOT.jar oauth2-app.jar

#
RUN sh -c 'touch /oauth2-app.jar'

EXPOSE 9195

#   entrypoint
ENTRYPOINT ["java", "-jar", "/oauth2-app.jar"]
