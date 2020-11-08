package me.cxis.mybatis.plugin.example4.interceptor;

import me.cxis.mybatis.plugin.example4.invocation.Invocation;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class TimeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        long startTime = System.nanoTime();
        System.out.println(String.format(
                "[%s]执行开始时间：%d；sql：%s",
                this.getClass().getSimpleName(),
                startTime,
                Arrays.asList(invocation.getArgs())
        ));

        Object result = invocation.invoke();

        long endTime = System.nanoTime();
        System.out.println(String.format(
                "[%s]执行结束时间：%d；sql：%s",
                this.getClass().getSimpleName(),
                endTime,
                Arrays.asList(invocation.getArgs())
        ));
        long coastTime = endTime - startTime;
        return result;
    }
}
