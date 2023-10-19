package fun.pullock.spring.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HystrixHelloCommand extends HystrixCommand<String> {

    private final String name;

    public HystrixHelloCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HystrixGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello: " + name;
    }

    @Override
    protected String getFallback() {
        return "access limit...";
    }
}
