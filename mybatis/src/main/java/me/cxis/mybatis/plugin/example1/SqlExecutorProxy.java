package me.cxis.mybatis.plugin.example1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class SqlExecutorProxy implements InvocationHandler {

    private Object target;

    public SqlExecutorProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("[%s]开始记录执行日志，执行的sql：%s", this.getClass().getSimpleName(), Arrays.asList(args)));
        long startTime = System.nanoTime();
        System.out.println(String.format("[%s]执行开始时间：%d；sql：%s", this.getClass().getSimpleName(), startTime, Arrays.asList(args)));
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();
        System.out.println(String.format("[%s]执行结束时间：%d；sql：%s", this.getClass().getSimpleName(), endTime, Arrays.asList(args)));
        long coastTime = endTime - startTime;
        System.out.println(String.format("[%s]执行消耗时间：%dns；sql：%s", this.getClass().getSimpleName(), coastTime, Arrays.asList(args)));
        System.out.println(String.format("[%s]结束记录执行日志，执行的sql：%s；结果：%s", this.getClass().getSimpleName(), Arrays.asList(args), result));
        return result;
    }

    public static <T> T getProxy(Object target) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new SqlExecutorProxy(target)
        );

    }
}
