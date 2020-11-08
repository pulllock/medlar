package me.cxis.mybatis.plugin.example5.interceptor;


import me.cxis.mybatis.plugin.example5.SqlExecutorProxy;
import me.cxis.mybatis.plugin.example5.invocation.Invocation;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {

    Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    default <T> T plugin(Object target) {
        return (T) SqlExecutorProxy.getProxy(target, this);
    }
}
