package me.cxis.spring.concurrency_number_limit.use_hystrix;

import me.cxis.spring.concurrency_number_limit.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public String getUserNameById(long userId) {
        try {
            return new GetUserNameByIdCommand(userId).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
