package fun.pullock.cache.cache2k.service;

import jakarta.annotation.Resource;
import fun.pullock.cache.cache2k.dao.model.UserDO;
import fun.pullock.cache.cache2k.dao.UserDao;
import fun.pullock.cache.cache2k.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static fun.pullock.cache.cache2k.cache.CacheNames.USER_BY_ID;
import static fun.pullock.cache.cache2k.cache.CacheNames.USER_BY_NAME;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Cacheable(value = USER_BY_ID, key = "#id", unless = "#result == null")
    public User queryById(Long id) {
        UserDO userDO = userDao.selectById(id);
        if (userDO == null) {
            return null;
        }

        return toUser(userDO);
    }

    @Cacheable(value = USER_BY_NAME, key = "#name", unless = "#result == null")
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
