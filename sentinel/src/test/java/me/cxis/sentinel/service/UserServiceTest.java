package me.cxis.sentinel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testGetUserNameById() throws InterruptedException {
        while (true) {
            userService.getUserNameById(1L);
            Thread.sleep(2000);
        }
    }
}
