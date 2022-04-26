# 使用

```
mvn clean package

docker build -t local_test/spring-boot-docker:1.0-SNAPSHOT .

docker run -p 8080:8080 --name spring-boot-docker -d local_test/spring-boot-docker:1.0-SNAPSHOT 

```