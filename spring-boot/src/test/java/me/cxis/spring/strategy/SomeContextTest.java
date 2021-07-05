package me.cxis.spring.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SomeContextTest {

    @Resource
    private SomeContext someContext;

    @Test
    public void testGetStrategy() {
        System.out.println(StrategyA.class.getName());
        System.out.println(StrategyA.class.getCanonicalName());
        System.out.println(StrategyA.class.getSimpleName());
        System.out.println(someContext.getStrategy("strategyA"));
    }
}
