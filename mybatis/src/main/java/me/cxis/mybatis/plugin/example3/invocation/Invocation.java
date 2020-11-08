package me.cxis.mybatis.plugin.example3.invocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invocation {

    private Object target;

    private Method method;

    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
