package me.cxis.aop.service.impl;

import me.cxis.aop.model.User;
import me.cxis.aop.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public User getById(Long id) {
        return new User(id, "test", 20);
    }
}
