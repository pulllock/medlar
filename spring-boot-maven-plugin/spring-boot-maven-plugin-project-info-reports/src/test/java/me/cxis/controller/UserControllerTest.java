package me.cxis.controller;

import me.cxis.AbstractIntegrationTest;
import me.cxis.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class UserControllerTest extends AbstractIntegrationTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    @Resource
    private UserController userController;

    @Test
    public void testQueryByUserId() {
        Long userId = 1L;
        User result = userController.queryByUserId(userId);
        LOGGER.info("User: {}", result);
    }

    @Test
    public void testQueryByUserName() {
        String userName = "name";
        User result = userController.queryByUserName(userName);
        LOGGER.info("User: {}", result);
    }
}
