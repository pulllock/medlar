package fun.pullock.cloud.gateway.server.nacos.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import fun.pullock.cloud.gateway.server.nacos.route.NacosRouteDefinitionRepository;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;


/**
 * Nacos动态路由配置
 */
//@Configuration
public class GatewayNacosConfiguration {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private NacosConfigManager nacosConfigManager;

    /**
     * 注册Nacos路由配置加载类
     * @return
     */
    @Bean
    public NacosRouteDefinitionRepository nacosRouteDefinitionRepository() {
        return new NacosRouteDefinitionRepository(nacosConfigManager, applicationEventPublisher);
    }
}
