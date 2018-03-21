package me.cxis.spring.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    public String getUserName(int userId) {
        return "xiaohong";
    }
}
