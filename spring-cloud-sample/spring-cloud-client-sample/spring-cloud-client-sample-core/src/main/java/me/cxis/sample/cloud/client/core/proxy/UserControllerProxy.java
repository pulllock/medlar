package me.cxis.sample.cloud.client.core.proxy;

import me.cxis.sample.cloud.user.api.controller.IUserController;
import me.cxis.sample.cloud.user.api.model.User;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

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
