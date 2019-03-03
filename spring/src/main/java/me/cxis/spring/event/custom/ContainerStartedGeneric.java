package me.cxis.spring.event.custom;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 只监听特定事件
 * @author chengxi
 */
@Component
public class ContainerStartedGeneric implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(event.getClass().getSimpleName());
        System.out.println("Container started!");
        System.out.println("after container started, i will start my job!");
    }
}
