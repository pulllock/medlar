package me.cxis.sample.dubbo3.provider.core;

import me.cxis.sample.dubbo3.provider.api.service.UserServiceGrpc.IUserService;
import org.apache.dubbo.config.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboProviderConfig {

    @Value("${dubbo.application.name}")
    private String appName;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Value("${dubbo.protocol.name}")
    private String protocolName;

    @Value("${dubbo.provider.timeout}")
    private Integer timeout;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress(registryAddress);
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        return protocolConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        return providerConfig;
    }

    @Bean
    public ServiceConfig<IUserService> userServiceServiceConfig(IUserService userService) {
        ServiceConfig<IUserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IUserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setTimeout(timeout);
        return serviceConfig;
    }
}
