package me.cxis.cache.redis.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.*;
import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.NON_FINAL;
import static java.nio.charset.StandardCharsets.UTF_8;
import static me.cxis.cache.redis.cache.CacheNames.USER_BY_ID;
import static me.cxis.cache.redis.cache.CacheNames.USER_BY_NAME;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("SpringBootCache::")
                .entryTtl(Duration.ofMinutes(1))
                .serializeKeysWith(fromSerializer(new StringRedisSerializer(UTF_8)))
                .serializeValuesWith(fromSerializer(genericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder
                .withCacheConfiguration(USER_BY_ID, redisCacheConfiguration().entryTtl(Duration.ofMinutes(1)))
                .withCacheConfiguration(USER_BY_NAME, redisCacheConfiguration().entryTtl(Duration.ofMinutes(1)))
                ;
    }

    private GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), NON_FINAL, As.PROPERTY);
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        return serializer;
    }
}
