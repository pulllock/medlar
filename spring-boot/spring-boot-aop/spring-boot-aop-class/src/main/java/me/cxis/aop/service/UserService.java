package me.cxis.aop.service;

import me.cxis.aop.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getById(Long id) {
        return new User(id, "test", 20);
    }
}
