package me.cxis.cloud.open.feign.client.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"me.cxis"})
public class FeignConfig {
}
