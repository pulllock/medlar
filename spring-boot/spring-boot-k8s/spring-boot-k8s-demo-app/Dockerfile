FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY target/spring-boot-k8s-demo-app-1.0.0-SNAPSHOT.jar spring-boot-k8s-demo-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-k8s-demo-app.jar"]