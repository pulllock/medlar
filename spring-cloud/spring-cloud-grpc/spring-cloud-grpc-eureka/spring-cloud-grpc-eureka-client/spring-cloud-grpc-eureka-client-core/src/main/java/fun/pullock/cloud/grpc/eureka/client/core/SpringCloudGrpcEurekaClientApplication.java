package fun.pullock.cloud.grpc.eureka.client.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGrpcEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGrpcEurekaClientApplication.class, args);
    }
}
