package me.cxis.spring.event.custom;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.getClass().getSimpleName());
        System.out.println("Custom event!!!");
    }
}
