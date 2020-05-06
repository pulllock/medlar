package me.cxis.spring;

import me.cxis.spring.service.PlaceHolderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class PlaceholderTest {

    @Resource
    private PlaceHolderServiceImpl placeHolderService;

    @Test
    public void testPlaceholder() {
        System.out.println(placeHolderService.getUserPrefix());
    }
}
