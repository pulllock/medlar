package me.cxis.maven.controller;

import me.cxis.maven.model.User;
import me.cxis.maven.AbstractIntegrationTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.Resource;

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
