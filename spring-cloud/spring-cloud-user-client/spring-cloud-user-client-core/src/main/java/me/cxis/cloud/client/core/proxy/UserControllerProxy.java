package me.cxis.cloud.client.core.proxy;

import jakarta.annotation.Resource;
import me.cxis.cloud.user.api.controller.IUserController;
import me.cxis.cloud.user.api.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserControllerProxy {

    @Resource
    private IUserController iUserController;

    public User queryUserById(Long userId) {
        try {
            return iUserController.queryUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
