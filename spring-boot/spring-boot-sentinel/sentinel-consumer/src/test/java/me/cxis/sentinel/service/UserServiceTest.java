package me.cxis.sentinel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testGetUserNameById(){
        while (true) {
            try {
                String userName = userService.getUserNameById(1L);
                System.out.println(userName);
            } catch (Exception e) {
                if (e.getMessage().contains("SentinelRpcException")) {
                    System.out.println("限流了。。。");
                } else {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
