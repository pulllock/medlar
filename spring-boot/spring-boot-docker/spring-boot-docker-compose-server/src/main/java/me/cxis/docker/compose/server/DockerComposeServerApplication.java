package me.cxis.docker.compose.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerComposeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerComposeServerApplication.class, args);
    }
}
