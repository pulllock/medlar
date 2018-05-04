package me.cxis.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by cheng.xi on 2017-10-14 09:57.
 */
public class MethodExample {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = MyObject.class;

        Method[] methods = clazz.getMethods();
        Arrays.stream(methods)
                .forEach(s -> System.out.println(s));

        Method method = clazz.getMethod("setAge", new Class[]{int.class});
        System.out.println(method);
        System.out.println(method.getName());

        Method method1 = clazz.getMethod("getAge", null);
        System.out.println(method1);

        Class[] parameterTypes = method.getParameterTypes();
        Arrays.stream(parameterTypes)
                .forEach(s -> System.out.println(s));

        Class returnType = method1.getReturnType();
        System.out.println(returnType);

        Method method2 = MyObject.class.getMethod("getName", null);
        MyObject instance = new MyObject();
        Object returnValue = method2.invoke(instance);
        System.out.println(returnValue);
    }
}
