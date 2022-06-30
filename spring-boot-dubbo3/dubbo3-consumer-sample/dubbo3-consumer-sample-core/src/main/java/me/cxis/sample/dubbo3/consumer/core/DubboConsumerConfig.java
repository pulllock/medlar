package me.cxis.sample.dubbo3.consumer.core;

import me.cxis.sample.dubbo3.provider.api.service.DubboUserServiceGrpc.IUserService;
import org.apache.dubbo.config.spring.ReferenceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConsumerConfig {

    @Bean
    public ReferenceBean<IUserService> userService() {
        return new ReferenceBean<>();
    }
}
