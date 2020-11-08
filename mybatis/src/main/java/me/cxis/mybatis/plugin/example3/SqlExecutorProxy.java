package me.cxis.mybatis.plugin.example3;

import me.cxis.mybatis.plugin.example3.interceptor.Interceptor;
import me.cxis.mybatis.plugin.example3.invocation.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SqlExecutorProxy implements InvocationHandler {

    private Object target;

    private Interceptor interceptor;

    public SqlExecutorProxy(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(target, method, args);
        return interceptor.intercept(invocation);
    }

    public static <T> T getProxy(Object target, Interceptor interceptor) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new SqlExecutorProxy(target, interceptor)
        );

    }
}
