package me.cxis.rpc.test1;

/**
 * Created by cheng.xi on 17-2-24.
 */
public class HelloWorldServiceImpl implements HelloWorldService{
    @Override
    public String sayHello(String msg) {
        String result = "Hello world" + msg;
        System.out.println(result);
        return result;
    }
}
