package fun.pullock.aop.service;

import fun.pullock.aop.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getById(Long id) {
        return new User(id, "test", 20);
    }
}
