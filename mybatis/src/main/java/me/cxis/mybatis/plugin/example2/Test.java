package me.cxis.mybatis.plugin.example2;


import me.cxis.mybatis.plugin.example2.interceptor.Interceptor;
import me.cxis.mybatis.plugin.example2.interceptor.LogInterceptor;
import me.cxis.mybatis.plugin.example2.interceptor.TimeInterceptor;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        SqlExecutor sqlExecutor = new DefaultSqlExecutor();

        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new LogInterceptor());
        interceptors.add(new TimeInterceptor());

        SqlExecutor proxy = SqlExecutorProxy.getProxy(sqlExecutor, interceptors);
        Object result = proxy.execute("select * from user where user_id = 1");
        System.out.println(result);
    }
}
