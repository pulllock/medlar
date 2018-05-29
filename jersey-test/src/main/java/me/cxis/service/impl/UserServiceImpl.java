package me.cxis.service.impl;

import me.cxis.model.User;
import me.cxis.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(long id) {
        User user = new User();
        user.setId(id);
        user.setAge(23);
        user.setName("小红");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        User user = new User();
        user.setId(1);
        user.setAge(23);
        user.setName("小明");
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }
}
