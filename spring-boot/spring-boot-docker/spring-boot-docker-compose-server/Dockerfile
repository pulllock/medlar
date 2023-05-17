# syntax=docker/dockerfile:1
FROM openjdk:8-jre-alpine
WORKDIR /app

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories && \
    apk update
COPY target/spring-boot-docker-compose-server-1.0.0-SNAPSHOT.jar spring-boot-docker-compose-server.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "spring-boot-docker-compose-server.jar"]