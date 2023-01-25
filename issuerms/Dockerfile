#   Dockerfile for Issuerms server
#
FROM openjdk:11

#   label image
LABEL Description="Issuerms Server" Version="0.0.1"

ARG VERSION=0.0.1

VOLUME /tmp

ADD target/issuerms-${VERSION}-SNAPSHOT.jar issuer-app.jar

#
RUN sh -c 'touch /issuer-app.jar'

EXPOSE 8099

#   entrypoint
ENTRYPOINT ["java", "-jar", "/issuer-app.jar"]
