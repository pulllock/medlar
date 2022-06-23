FROM openjdk:8-jre-alpine

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories && \
    apk update && \
    mkdir -p /app
COPY target/spring-boot-docker-compose-client-1.0.0-SNAPSHOT.jar app/spring-boot-docker-compose-client.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app/spring-boot-docker-compose-client.jar"]