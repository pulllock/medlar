package fun.pullock.cloud.open.feign.async.client.config;

import feign.Logger;
import feign.RequestInterceptor;
import fun.pullock.cloud.open.feign.async.client.feign.FeignSlf4jInfoLogger;
import fun.pullock.cloud.open.feign.async.client.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"fun.pullock"})
public class FeignConfig {

    @Bean
    public Logger logger() {
        return new FeignSlf4jInfoLogger();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
