package me.cxis.mybatis.plugin.example3.interceptor;

import me.cxis.mybatis.plugin.example3.invocation.Invocation;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {

    Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException;
}
