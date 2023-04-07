package me.cxis.cloud.gateway.server.nacos.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import me.cxis.cloud.gateway.server.nacos.route.NacosRouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

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
