package me.cxis.spring.autowire.xml.service.impl;

import me.cxis.spring.autowire.xml.dao.UserDao;
import me.cxis.spring.autowire.xml.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String getUserName(Long userId) {
        return userDao.queryUserName(userId);
    }
}
