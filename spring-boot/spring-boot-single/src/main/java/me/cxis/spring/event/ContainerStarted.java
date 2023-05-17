package me.cxis.spring.event;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 监听容器启动
 * 相当于观察者模式的观察者
 *
 * 当容器启动后，会调用这里，通知我们做一些事情
 * @author dachengxi
 */
/// @Component
public class ContainerStarted implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.getClass().getSimpleName());
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("Container started!");
            System.out.println("after container started, i will start my job!");
        }
    }
}
