package fun.pullock.aop.service.impl;

import fun.pullock.aop.model.User;
import fun.pullock.aop.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public User getById(Long id) {
        return new User(id, "test", 20);
    }
}
