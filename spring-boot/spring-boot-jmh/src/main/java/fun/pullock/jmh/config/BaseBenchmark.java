package fun.pullock.jmh.config;

import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;

import java.io.IOException;

/**
 * JMH基类
 */
public abstract class BaseBenchmark {

    @Setup
    public void init() {
    }

    @TearDown
    public void close() throws IOException {
    }
}
