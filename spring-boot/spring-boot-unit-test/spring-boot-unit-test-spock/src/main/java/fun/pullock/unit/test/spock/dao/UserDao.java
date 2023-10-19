package fun.pullock.unit.test.spock.dao;

import fun.pullock.unit.test.spock.dao.mapper.UserDOMapper;
import fun.pullock.unit.test.spock.dao.model.UserDO;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

@Repository
public class UserDao {

    @Resource
    private UserDOMapper userDOMapper;

    public UserDO selectById(Long id) {
        return userDOMapper.selectByPrimaryKey(id);
    }
}
