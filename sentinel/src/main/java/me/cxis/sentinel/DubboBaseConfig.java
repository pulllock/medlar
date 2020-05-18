package me.cxis.sentinel;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class DubboBaseConfig {

    @Value("${dubbo.registry.url}")
    protected String dubboRegistryUrl;

    @Value("${dubbo.application.name}")
    protected String dubboApplicationName;

    @Value("${dubbo.provider.version}")
    protected String dubboProviderVersion;

    @Value("${dubbo.provider.retries}")
    protected int dubboProviderRetries;

    @Value("${dubbo.provider.timeout}")
    protected int dubboProviderTimeout;

    @Value("${dubbo.provider.port}")
    protected int dubboProviderPort;

    @Value("${dubbo.consumer.version}")
    protected String dubboConsumerVersion;

    @Value("${dubbo.consumer.retries}")
    protected int dubboConsumerRetries;

    @Value("${dubbo.consumer.timeout}")
    protected int dubboConsumerTimeout;

    @Value("${dubbo.consumer.check}")
    protected boolean dubboConsumerCheck = false;



    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubboRegistryUrl);
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubboApplicationName);
        return applicationConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }
}
