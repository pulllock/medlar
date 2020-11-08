package me.cxis.mybatis.plugin.example5.interceptor;

import java.util.ArrayList;
import java.util.List;

public class InterceptorChain {

    List<Interceptor> interceptors = new ArrayList<>();

    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }
}
