package me.cxis.spring.autowire.xml.dao.impl;

import me.cxis.spring.autowire.xml.dao.UserDao;

public class UserDaoImpl1 implements UserDao {

    public String queryUserName(Long userId) {
        return "xxxx1";
    }
}
