package me.cxis.json.gson.config;

import com.google.gson.*;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class GsonConfig {

    private static final DateTimeFormatter LOCAL_TATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Bean
    public GsonBuilderCustomizer gsonBuilderCustomizer() {
        return new GsonBuilderCustomizer() {
            @Override
            public void customize(GsonBuilder gsonBuilder) {
                gsonBuilder.registerTypeHierarchyAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {

                    @Override
                    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(LOCAL_TATE_TIME_FORMATTER.format(src));
                    }
                }).registerTypeHierarchyAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsString(), LOCAL_TATE_TIME_FORMATTER);
                    }
                })
                ;
            }
        };
    }
}
