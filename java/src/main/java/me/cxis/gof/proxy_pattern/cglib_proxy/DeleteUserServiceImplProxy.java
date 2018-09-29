package me.cxis.gof.proxy_pattern.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DeleteUserServiceImplProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object newProxyInstance(Class<?> clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (checkUser((Long) args[0])) {
            System.out.println(String.format("User: %s has delete permission", args[0]));
            methodProxy.invokeSuper(o, args);
        } else {
            System.out.println(String.format("User: %s has no delete permission", args[0]));
        }
        return null;
    }

    private boolean checkUser(long id) {
        if (id > 100) {
            return false;
        }
        return true;
    }
}
