package fun.pullock.cache.redis.service;

import fun.pullock.cache.redis.cache.CacheNames;
import fun.pullock.cache.redis.dao.UserDao;
import fun.pullock.cache.redis.dao.model.UserDO;
import fun.pullock.cache.redis.model.User;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Cacheable(value = CacheNames.USER_BY_ID, key = "#id", unless = "#result == null")
    public User queryById(Long id) {
        UserDO userDO = userDao.selectById(id);
        if (userDO == null) {
            return null;
        }

        return toUser(userDO);
    }

    @Cacheable(value = CacheNames.USER_BY_NAME, key = "#name", unless = "#result == null")
    public User queryByName(String name) {
        UserDO userDO = userDao.selectByName(name);
        if (userDO == null) {
            return null;
        }

        return toUser(userDO);
    }

    private User toUser(UserDO source) {
        if (source == null) {
            return null;
        }

        User target = new User();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setGender(source.getGender());
        target.setBirthDay(source.getBirthDay());
        return target;
    }
}
