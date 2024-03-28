package fun.pullock.jmh.config;

import fun.pullock.jmh.SpringBootJMHApplication;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class SpringContext {

    private static volatile ConfigurableApplicationContext context;

    public static void setContext() {
        context = SpringApplication.run(SpringBootJMHApplication.class);
    }

    public static void autowireBean(Object bean) {
        AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
        factory.autowireBean(bean);
    }

    public static void close() throws IOException {
        context.close();
    }
}
