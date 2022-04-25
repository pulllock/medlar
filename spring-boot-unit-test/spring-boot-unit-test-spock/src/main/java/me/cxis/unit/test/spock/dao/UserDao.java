package me.cxis.unit.test.spock.dao;

import me.cxis.unit.test.spock.dao.mapper.UserDOMapper;
import me.cxis.unit.test.spock.dao.model.UserDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {

    @Resource
    private UserDOMapper userDOMapper;

    public UserDO selectById(Long id) {
        return userDOMapper.selectByPrimaryKey(id);
    }
}
