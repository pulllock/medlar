package me.cxis.dubbo3.provider.core;

import me.cxis.dubbo3.provider.api.service.DubboUserServiceGrpc.IUserService;
import org.apache.dubbo.config.spring.ServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboProviderConfig {

    @Bean
    public ServiceBean<IUserService> userServiceServiceBean(IUserService userService) {
        ServiceBean<IUserService> serviceBean = new ServiceBean<>();
        serviceBean.setInterface(IUserService.class);
        serviceBean.setRef(userService);
        return serviceBean;
    }
}
