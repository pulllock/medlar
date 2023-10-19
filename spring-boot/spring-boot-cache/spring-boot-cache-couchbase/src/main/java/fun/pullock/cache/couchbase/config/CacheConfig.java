package fun.pullock.cache.couchbase.config;

import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.codec.JsonTranscoder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import fun.pullock.cache.couchbase.cache.CacheNames;
import fun.pullock.cache.couchbase.constants.DateTimeFormatConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CouchbaseCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.cache.CouchbaseCacheConfiguration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.NON_FINAL;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * 缓存配置
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public CouchbaseCacheManagerBuilderCustomizer couchbaseCacheManagerBuilderCustomizer() {
        return builder -> builder
                // 默认配置
                .cacheDefaults(couchbaseCacheConfiguration())
                .withCacheConfiguration(CacheNames.USER_BY_ID, couchbaseCacheConfiguration().entryExpiry(Duration.ofMinutes(1)))
                .withCacheConfiguration(CacheNames.USER_BY_NAME, couchbaseCacheConfiguration().entryExpiry(Duration.ofMinutes(2)))
                ;
    }

    private CouchbaseCacheConfiguration couchbaseCacheConfiguration() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 注册自定义的时间序列化和反序列化格式
        objectMapper.registerModule(customJavaTimeModule());
        // 在属性中添加@class信息，用于反序列化时所需的类型信息，反序列化时如果没有类型信息会出错
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // 反序列化时遇到未知属性不抛异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化时空对象不抛异常
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        return CouchbaseCacheConfiguration.defaultCacheConfig()
                .valueTranscoder(JsonTranscoder.create(JacksonJsonSerializer.create(objectMapper)))
                // 缓存命名空间前缀
                .prefixCacheNameWith(String.format("%s::", appName))
                // 默认缓存时间：5分钟
                .entryExpiry(Duration.ofMinutes(5))
                // 不缓存null值
                .disableCachingNullValues()
                ;
    }

    /**
     * 自定义Java时间序列化和反序列化格式
     * @return
     */
    private JavaTimeModule customJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // LocalDateTime序列化格式
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(ofPattern(DateTimeFormatConstant.DEFAULT_DATE_TIME_FORMAT)));
        // LocalDate序列化格式
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(ofPattern(DateTimeFormatConstant.DEFAULT_DATE_FORMAT)));
        // LocalTime序列化格式
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(ofPattern(DateTimeFormatConstant.DEFAULT_TIME_FORMAT)));
        // LocalDateTime反序列化格式
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(ofPattern(DateTimeFormatConstant.DEFAULT_DATE_TIME_FORMAT)));
        // LocalDate反序列化格式
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(ofPattern(DateTimeFormatConstant.DEFAULT_DATE_FORMAT)));
        // LocalTime反序列化格式
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(ofPattern(DateTimeFormatConstant.DEFAULT_TIME_FORMAT)));
        return javaTimeModule;
    }
}
