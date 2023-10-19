package fun.pullock.cloud.open.feign.client.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"fun.pullock"})
public class FeignConfig {
}
