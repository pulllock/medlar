package fun.pullock.dubbo3.consumer.core;

import fun.pullock.dubbo3.provider.api.service.DubboUserServiceGrpc.IUserService;
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
