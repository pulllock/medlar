package me.cxis.rpc.test1;

/**
 * Created by cheng.xi on 17-2-24.
 */
public class Test {
    public static void main(String[] args) {
        HelloWorldService helloWorldService = (HelloWorldService) RPCProxyClient.getProxy(HelloWorldService.class);
        helloWorldService.sayHello("test");
    }
}
