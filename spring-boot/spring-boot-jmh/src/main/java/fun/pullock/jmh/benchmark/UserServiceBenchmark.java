package fun.pullock.jmh.benchmark;

import fun.pullock.jmh.config.BaseBenchmarkSpring;
import fun.pullock.jmh.service.UserService;
import jakarta.annotation.Resource;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(value = Scope.Benchmark)
public class UserServiceBenchmark extends BaseBenchmarkSpring {

    @Resource
    private UserService userService;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(UserServiceBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public Long generateUserId() {
        return userService.generateUserId();
    }
}
