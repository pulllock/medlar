package me.cxis.spring.context;

import me.cxis.spring.service.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationContextUtilTest {

    @Test
    public void testListBeanNames(){
        String[] beanNames = ApplicationContextUtil.listBeanNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Test
    public void testGetBean() {
        AopService aopService = (AopService) ApplicationContextUtil.getBean("aopService");
        System.out.println(aopService.calculateSomething());
    }
}
