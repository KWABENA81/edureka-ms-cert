#   Dockerfile for bookms server
#
FROM openjdk:11

ENV JAVA_VERSION=11.0.16

ENV JAVA_HOME=/usr/local/openjdk-11

#   label image
LABEL Description="Bookms Server" Version="0.0.1"

ARG VERSION=0.0.1

VOLUME /tmp

ADD target/bookms-${VERSION}-SNAPSHOT.jar book-app.jar

#
RUN sh -c 'touch /book-app.jar'

EXPOSE 8097

#   entrypoint
ENTRYPOINT ["java", "-jar", "/book-app.jar"]