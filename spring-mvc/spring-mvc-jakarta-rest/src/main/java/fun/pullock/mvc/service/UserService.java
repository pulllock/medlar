package fun.pullock.mvc.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserName(Long id) {
        return id + "xxx";
    }
}
