package me.cxis.mybatis.plugin.example2;

import me.cxis.mybatis.plugin.example2.interceptor.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class SqlExecutorProxy implements InvocationHandler {

    private Object target;

    private List<Interceptor> interceptors;

    public SqlExecutorProxy(Object target, List<Interceptor> interceptors) {
        this.target = target;
        this.interceptors = interceptors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (Interceptor interceptor : interceptors) {
            interceptor.intercept();
        }
        Object result = method.invoke(target, args);
        return result;
    }

    public static <T> T getProxy(Object target, List<Interceptor> interceptors) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new SqlExecutorProxy(target, interceptors)
        );

    }
}
