package me.cxis.unit.test.junit5.dao;

import me.cxis.unit.test.junit5.dao.mapper.UserDOMapper;
import me.cxis.unit.test.junit5.dao.model.UserDO;
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
