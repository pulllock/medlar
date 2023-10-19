package fun.pullock.cloud.open.feign.client.proxy;

import fun.pullock.cloud.open.feign.server.api.model.User;
import fun.pullock.cloud.open.feign.server.api.model.common.Result;
import fun.pullock.cloud.open.feign.server.api.query.UserQuery;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProxy {

    @Resource
    private UserService userService;

    public User queryUserById(Long userId) {
        try {
            Result<User> result = userService.queryUserById(userId);
            if (result == null || !result.isSuccess()) {
                return null;
            }
            return result.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User queryUserByCondition(UserQuery userQuery) {
        try {
            Result<User> result = userService.queryByCondition(userQuery);
            if (result == null || !result.isSuccess()) {
                return null;
            }
            return result.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
