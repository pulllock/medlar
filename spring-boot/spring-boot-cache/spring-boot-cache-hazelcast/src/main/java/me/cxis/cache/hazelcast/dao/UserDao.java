package me.cxis.cache.hazelcast.dao;

import me.cxis.cache.hazelcast.dao.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    public UserDO selectById(Long id) {
        LOGGER.info("query by id from database, id: {}", id);

        if (id == 2) {
            return null;
        }

        // MOCK
        UserDO userDO = new UserDO();
        userDO.setId(id);
        userDO.setName("name_" + id);
        userDO.setBirthDay(LocalDateTime.now());
        userDO.setGender(1);
        return userDO;
    }

    public UserDO selectByName(String name) {
        LOGGER.info("query by name from database, name: {}", name);

        if (name.length() >= 10) {
            return null;
        }

        // MOCK
        UserDO userDO = new UserDO();
        userDO.setId(System.currentTimeMillis());
        userDO.setName("name_" + name);
        userDO.setBirthDay(LocalDateTime.now());
        userDO.setGender(2);
        return userDO;
    }
}
