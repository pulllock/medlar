package me.cxis.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class HystrixControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HystrixController hystrixController;

    @Test
    public void testHello() {
        String result = hystrixController.hello();
        System.out.println(result);
    }

    @Test(threadPoolSize = 100, invocationCount = 1000)
    public void testConcurrentHello() {
        String result = hystrixController.hello();
        System.out.println(result);
    }

    @Test(threadPoolSize = 100, invocationCount = 1000)
    public void testConcurrentHystrixThreadPool() {
        String result = hystrixController.hystrixThreadPool();
        System.out.println(result);
    }

    @Test(threadPoolSize = 100, invocationCount = 1000)
    public void testConcurrentHystrixSemaphore() {
        String result = hystrixController.hystrixSemaphreo();
        System.out.println(result);
    }
}
