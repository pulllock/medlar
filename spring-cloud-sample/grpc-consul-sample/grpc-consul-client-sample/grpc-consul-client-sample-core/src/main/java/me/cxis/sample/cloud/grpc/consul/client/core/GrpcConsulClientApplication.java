package me.cxis.sample.cloud.grpc.consul.client.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GrpcConsulClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcConsulClientApplication.class, args);
    }
}