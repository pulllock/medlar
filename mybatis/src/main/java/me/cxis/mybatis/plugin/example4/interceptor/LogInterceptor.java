package me.cxis.mybatis.plugin.example4.interceptor;

import me.cxis.mybatis.plugin.example4.invocation.Invocation;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class LogInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println(String.format(
                "[%s]开始记录执行日志，执行的sql：%s",
                this.getClass().getSimpleName(),
                Arrays.asList(invocation.getArgs())
        ));

        Object result = invocation.invoke();

        System.out.println(String.format(
                "[%s]结束记录执行日志，执行的sql：%s；结果：%s",
                this.getClass().getSimpleName(),
                Arrays.asList(invocation.getArgs()),
                result
        ));
        return result;
    }
}
