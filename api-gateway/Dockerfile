#   Dockerfile for api-gateway server
#
FROM openjdk:11

#   label image
LABEL Description="api-gateway Server" Version="0.0.1"

ARG VERSION=0.0.1

VOLUME /tmp

ADD target/api-gateway-${VERSION}-SNAPSHOT.jar gateway-app.jar

#
RUN sh -c 'touch /gateway-app.jar'

EXPOSE 9797

#   entrypoint
ENTRYPOINT ["java", "-jar", "/gateway-app.jar"]

