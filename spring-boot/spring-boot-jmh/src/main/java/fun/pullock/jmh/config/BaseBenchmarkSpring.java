package fun.pullock.jmh.config;

import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;

import java.io.IOException;

/**
 * JMH基类，需要启动Spring容器的场景下使用
 */
public abstract class BaseBenchmarkSpring {

    @Setup
    public void init() {
        SpringContext.setContext();
        SpringContext.autowireBean(this);
    }

    @TearDown
    public void close() throws IOException {
        SpringContext.close();
    }
}
