package me.cxis.mvc.controller;

import me.cxis.mvc.service.IndexService;
import me.cxis.mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private IndexService indexService;

    private ApplicationContext applicationContext;

    @RequestMapping("/")
    public String index() {
        String userName = userService.getUserName(1L);
        LOGGER.info("userName: {}", userName);
        return indexService.getIndexName();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println(applicationContext);
    }
}
