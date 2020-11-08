package me.cxis.mybatis.plugin.example2.interceptor;

public class TimeInterceptor implements Interceptor {

    @Override
    public void intercept() {
        long startTime = System.nanoTime();
        System.out.println(String.format("[%s]执行开始时间：%d；sql：null", this.getClass().getSimpleName(), startTime));
    }
}
