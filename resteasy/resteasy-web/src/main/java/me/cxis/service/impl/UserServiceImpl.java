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
        user.setName("小明");
        user.setAge(24);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        User user = new User();
        user.setId(1);
        user.setName("小明");
        user.setAge(24);

        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }
}
