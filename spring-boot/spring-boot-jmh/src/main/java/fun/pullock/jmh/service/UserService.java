package fun.pullock.jmh.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {

    public Long generateUserId() {
        return ThreadLocalRandom.current().nextLong();
    }
}
