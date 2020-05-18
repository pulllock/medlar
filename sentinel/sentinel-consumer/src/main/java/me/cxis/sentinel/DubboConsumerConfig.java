package me.cxis.sentinel;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import me.cxis.sentinel.service.UserService;
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

    @Bean
    public ReferenceBean<UserService> userServiceReferenceBean() {
        ReferenceBean<UserService> referenceBean = new ReferenceBean<>();
        referenceBean.setInterface(UserService.class);
        referenceBean.setVersion(dubboConsumerVersion);
        referenceBean.setRetries(dubboConsumerRetries);
        referenceBean.setTimeout(dubboConsumerTimeout);
        referenceBean.setCheck(dubboConsumerCheck);
        return referenceBean;
    }

}
