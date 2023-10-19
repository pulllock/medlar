package fun.pullock.websocket.netty.cluster.redis.service;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void pushMessageToUser(String userId, String message) {
        // TODO
    }

    public void pushMessageToAllUser(String message) {
        stringRedisTemplate.convertAndSend("REDIS_WEBSOCKET_MESSAGE", message);
    }
}
