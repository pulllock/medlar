package me.cxis.cloud.gateway.server.nacos.route;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 从Nacos配置中获取动态路由
 */
public class NacosRouteDefinitionRepository implements RouteDefinitionRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(NacosRouteDefinitionRepository.class);

    /**
     * Nacos中配置的Data ID
     */
    private final static String DATA_ID = "gateway-dynamic-route";

    /**
     * Nacos中配置的Group
     */
    private final static String GROUP = "DEFAULT_GROUP";

    private NacosConfigManager nacosConfigManager;

    private ApplicationEventPublisher applicationEventPublisher;

    public NacosRouteDefinitionRepository(NacosConfigManager nacosConfigManager, ApplicationEventPublisher applicationEventPublisher) {
        this.nacosConfigManager = nacosConfigManager;
        this.applicationEventPublisher = applicationEventPublisher;
        addListener();
    }

    /**
     * 从Nacos配置中获取动态路由配置
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            String configContent = nacosConfigManager.getConfigService().getConfig(DATA_ID, GROUP, 5000);
            if (StringUtils.isNotEmpty(configContent)) {
                List<RouteDefinition> routeDefinitions = JSON.parseArray(configContent, RouteDefinition.class);
                return Flux.fromIterable(routeDefinitions);
            }
        } catch (NacosException e) {
            LOGGER.error("Get route definitions from nacos error, cause: ", e);
        }
        return Flux.fromIterable(new ArrayList<>());
    }

    /**
     * 保存动态路由，Nacos配置方式不支持
     * @param route
     * @return
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除动态路由，Nacos配置方式不支持
     * @param routeId
     * @return
     */
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        throw new UnsupportedOperationException();
    }

    /**
     * 配置Nacos监听器，监听动态路由配置信息的变更，更新gateway的路由信息
     */
    private void addListener() {
        try {
            nacosConfigManager.getConfigService().addListener(DATA_ID, GROUP, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    // 发送一个RefreshRoutesEvent事件即可
                    applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
                }
            });
        } catch (NacosException e) {
            LOGGER.error("Add nacos listener error, cause: ", e);
        }
    }
}
