package me.cxis.sample.cloud.gateway.server.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;

@Configuration
public class RedisConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * 使用Redis订阅路由变动的消息，用来刷新服务器的路由配置
     * @param applicationEventPublisher
     * @param connectionFactory
     * @return
     */
    @Bean
    public ReactiveRedisMessageListenerContainer reactiveRedisMessageListenerContainer(
            ApplicationEventPublisher applicationEventPublisher,
            ReactiveRedisConnectionFactory connectionFactory) {
        ReactiveRedisMessageListenerContainer container = new ReactiveRedisMessageListenerContainer(connectionFactory);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            container.destroyLater().subscribe();
        }));
        container.receive(new ChannelTopic("gateway-routes-refresh"))
                .map(ReactiveSubscription.Message::getMessage)
                .subscribe(message -> {
                    LOGGER.info("Received gateway routes refresh message: {}", message);
                    applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
                });

        return container;
    }
}
