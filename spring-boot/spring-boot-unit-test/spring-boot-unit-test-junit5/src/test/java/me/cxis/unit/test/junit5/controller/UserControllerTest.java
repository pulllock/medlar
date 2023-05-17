package me.cxis.unit.test.junit5.controller;

import me.cxis.unit.test.junit5.AbstractIntegrationTest;
import me.cxis.unit.test.junit5.model.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import jakarta.annotation.Resource;

public class UserControllerTest extends AbstractIntegrationTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    @Resource
    private UserController userController;

    @Test
    public void testGetUserById() {
        UserVO result = userController.queryById(1L);
        LOGGER.info(result.toString());
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetUserByIdHttp() {
        ResponseEntity<UserVO> responseEntity = testRestTemplate.getForEntity(HOST + port + "/user/queryById?id=1", UserVO.class);
        LOGGER.info(responseEntity.toString());
        Assertions.assertNotNull(responseEntity);
        UserVO result = responseEntity.getBody();
        Assertions.assertNotNull(result);
    }
}
