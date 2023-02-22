package me.cxis.sample.cloud.grpc.eureka.server.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GrpcEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcEurekaServerApplication.class, args);
    }
}
