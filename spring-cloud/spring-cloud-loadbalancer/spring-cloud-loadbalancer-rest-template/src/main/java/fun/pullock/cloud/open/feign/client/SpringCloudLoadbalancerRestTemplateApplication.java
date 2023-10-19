package fun.pullock.cloud.open.feign.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudLoadbalancerRestTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudLoadbalancerRestTemplateApplication.class, args);
    }
}
