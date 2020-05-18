package me.cxis.sentinel;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConsumerConfig extends DubboBaseConfig {

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(dubboConsumerCheck);
        return consumerConfig;
    }

    @Bean
    public ReferenceConfig<?> referenceConfig() {
        ReferenceConfig<?> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setMonitor(monitorConfig());
        return referenceConfig;
    }


}
