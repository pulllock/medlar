package me.cxis.mybatis.plugin.example4;

import me.cxis.mybatis.plugin.example4.interceptor.Interceptor;
import me.cxis.mybatis.plugin.example4.interceptor.LogInterceptor;
import me.cxis.mybatis.plugin.example4.interceptor.TimeInterceptor;

public class Test {

    public static void main(String[] args) {
        SqlExecutor sqlExecutor = new DefaultSqlExecutor();

        Interceptor timeInterceptor = new TimeInterceptor();
        SqlExecutor proxy = timeInterceptor.plugin(sqlExecutor);

        Interceptor logInterceptor = new LogInterceptor();
        proxy = logInterceptor.plugin(proxy);


        Object result = proxy.execute("select * from user where user_id = 1");
        System.out.println(result);
    }
}
