package me.cxis.redis.service;

import me.cxis.redis.RedisClient;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static me.cxis.redis.constants.RedisKeys.*;

@Service("rankService")
public class RankService {

    @Resource
    private RedisClient redisClient;
    
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 给用户id=1的用户添加10个好友
     * @return
     */
    public boolean addFriend() {
        Long userId = 1L;
        String key = friendRelationPrefix + userId;
        for (int friendId = 2; friendId <= 11; friendId++) {
            redisClient.sAdd(key, String.valueOf(friendId));
        }
        return true;
    }

    /**
     * 添加步数
     * @param userId
     * @param steps
     * @return
     */
    public boolean addSteps(Long userId, long steps) {
        String key = userStepsPrefix + userId;
        return redisClient.incrBy(key, steps);
    }

    /**
     * 将步数初始化添加进排行榜中
     * @return
     */
    public boolean rank() {
        Long userId = 1L;
        String key = userRankPrefix + userId;
        Long memberCount = redisClient.zCard(key);

        // zset中还没有排名，需要初始化整个排名
        if (memberCount == 0) {
            for (int friendId = 1; friendId <= 11; friendId++) {
                String stepsKey = userStepsPrefix + friendId;
                Long steps = Long.valueOf((String) redisClient.get(stepsKey));

                redisClient.zAdd(key, (long) friendId, steps);
            }
        }

        return true;
    }

    /**
     * 使用pipeline方式将步数初始化添加进排行榜中
     * @return
     */
    public boolean rankPipeline() {
        Long userId = 1L;
        String key = userRankPrefix + userId;
        Long memberCount = redisClient.zCard(key);

        // zset中还没有排名，需要初始化整个排名
        if (memberCount == 0) {
            Map<Long, Long> userSteps = new HashMap<>();
            for (int friendId = 1; friendId <= 11; friendId++) {
                String stepsKey = userStepsPrefix + friendId;
                Long steps = Long.valueOf((String) redisClient.get(stepsKey));
                userSteps.put((long) friendId, steps);
            }

            if (userSteps.size() > 0) {
                redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
                    connection.openPipeline();

                    RedisSerializer<String> keySerializer = redisTemplate.getKeySerializer();
                    RedisSerializer<String> valueSerializer = redisTemplate.getValueSerializer();

                    for (Map.Entry<Long, Long> userStep : userSteps.entrySet()) {
                        Long steps = userStep.getValue();
                        Long friendId = userStep.getKey();
                        connection.zAdd(keySerializer.serialize(key), steps, valueSerializer.serialize(String.valueOf(friendId)));
                    }

                    connection.closePipeline();
                    return null;
                });
            }
        }

        return true;
    }

    /**
     * 获取top排名列表，map中key是userId，value是排名-步数
     * @return
     */
    public Map<Long, String> getTopRankList(Integer top) {
        Long userId = 1L;
        String key = userRankPrefix + userId;

        Set<Object> topUser = redisClient.zRevRange(key, 0, top);
        Map<Long, String> result = new LinkedHashMap<>();
        if (topUser != null && topUser.size() > 0) {
            for (Object user : topUser) {
                Long friendId = Long.valueOf((String) user);
                Long rank = redisClient.zRevRank(key, String.valueOf(friendId));
                Double steps = redisClient.zScore(key, String.valueOf(friendId));
                result.put(friendId, rank + "-" + steps);
            }
        }

        return result;
    }
}
