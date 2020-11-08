package me.cxis.mybatis.plugin.example2.interceptor;

public class LogInterceptor implements Interceptor {

    @Override
    public void intercept() {
        System.out.println(String.format("[%s]开始记录执行日志，执行的sql：null", this.getClass().getSimpleName()));
    }
}
