package me.cxis.sentinel.service.impl;

import me.cxis.sentinel.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public String getUserNameById(Long userId) {
        System.out.println("userId: " + userId);
        return "userName_" + userId;
    }
}
