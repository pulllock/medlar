package me.cxis.spring.service.impl;

import me.cxis.spring.dao.UserDao;
import me.cxis.spring.service.UserService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public String getUserName(int userId) {
        return userDao.getUserName(userId);
    }
}
