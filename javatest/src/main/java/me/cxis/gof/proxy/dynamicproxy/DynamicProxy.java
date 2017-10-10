package me.cxis.gof.proxy.dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by cheng.xi on 2017-04-12 19:59.
 * 代理
 */
public class DynamicProxy implements InvocationHandler{

    private Object target;

    public DynamicProxy(Object target){
        this.target = target;
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("办事之前先收取点费用");
        System.out.println("开始办事");
        Object result = method.invoke(target,args);
        System.out.println("办完了");
        return result;
    }
}
