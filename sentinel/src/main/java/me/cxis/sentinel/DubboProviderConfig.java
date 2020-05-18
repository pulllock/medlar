package me.cxis.sentinel;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import me.cxis.sentinel.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboProviderConfig extends DubboBaseConfig {

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(dubboProviderPort);
        return protocolConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        return providerConfig;
    }

    @Bean
    public ServiceBean<UserService> userServiceServiceBean(UserService userService) {
        ServiceBean<UserService> serviceBean = new ServiceBean<>();
        serviceBean.setVersion(dubboProviderVersion);
        serviceBean.setInterface(UserService.class);
        serviceBean.setRef(userService);
        serviceBean.setTimeout(dubboProviderTimeout);
        return serviceBean;
    }
}
