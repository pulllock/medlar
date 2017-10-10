package me.cxis.rpc.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by cheng.xi on 17-2-24.
 */
public class RPCProxyClient implements InvocationHandler{

    private Object object;

    public RPCProxyClient(Object object) {
        this.object = object;
    }

public static Object getProxy(Object object){
    return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),new RPCProxyClient(object));
}
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = new Object();
        System.out.println("执行通信相关逻辑");
        return result;
    }
}
