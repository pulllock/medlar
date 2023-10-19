package fun.pullock.cache.redis.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/set")
public class SetController {

    @Value("${spring.application.name}")
    private String appName;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/add")
    public Long add(@RequestParam("id") Long id) {
        String key = String.format(
                "%s::Ids::%s",
                appName,
                DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now())
        );
        Long result = redisTemplate.opsForSet().add(key, id);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
        return result;
    }

    @GetMapping("/remove")
    public Long remove(@RequestParam("ids") List<Long> ids) {
        String key = String.format(
                "%s::Ids::%s",
                appName,
                DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now())
        );
        return redisTemplate.opsForSet().remove(key, ids.toArray());
    }

    @GetMapping("/get")
    public List<Long> get() {
        String key = String.format(
                "%s::Ids::%s",
                appName,
                DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now())
        );
        Set<Object> members = redisTemplate.opsForSet().members(key);
        if (members == null || members.size() == 0) {
            return null;
        }
        return members.stream().map(m -> ((Number)m).longValue()).collect(Collectors.toList());
    }
}
