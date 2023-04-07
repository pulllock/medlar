package me.cxis.cloud.grpc.consul.server.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGrpcConsulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGrpcConsulServerApplication.class, args);
    }
}
