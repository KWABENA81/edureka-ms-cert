#   Dockerfile for eureka server
#
FROM openjdk:11

#   label image
LABEL Description="Eureka Server" Version="0.0.1"

ARG VERSION=0.0.1

VOLUME /tmp

ADD target/eureka-server-${VERSION}-SNAPSHOT.jar eureka-server-app.jar

#
RUN sh -c 'touch /eureka-server-app.jar'

EXPOSE 8761

#   entrypoint
ENTRYPOINT ["java", "-jar", "/eureka-server-app.jar"]
