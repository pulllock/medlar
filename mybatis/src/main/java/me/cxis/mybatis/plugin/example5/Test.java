package me.cxis.mybatis.plugin.example5;

import me.cxis.mybatis.plugin.example5.interceptor.InterceptorChain;
import me.cxis.mybatis.plugin.example5.interceptor.LogInterceptor;
import me.cxis.mybatis.plugin.example5.interceptor.TimeInterceptor;

public class Test {

    public static void main(String[] args) {
        InterceptorChain interceptorChain = new InterceptorChain();
        interceptorChain.addInterceptor(new TimeInterceptor());
        interceptorChain.addInterceptor(new LogInterceptor());

        SqlExecutor sqlExecutor = new DefaultSqlExecutor();
        SqlExecutor proxy = (SqlExecutor) interceptorChain.pluginAll(sqlExecutor);
        Object result = proxy.execute("select * from user where user_id = 1;");
        System.out.println(result);
    }
}
