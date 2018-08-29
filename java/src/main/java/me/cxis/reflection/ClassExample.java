package me.cxis.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by cheng.xi on 2017-10-14 09:27.
 */
public class ClassExample {
    public static void main(String[] args) throws ClassNotFoundException {
        Class myObjectClass = MyObject.class;

        String name = "me.cxis.reflection.MyObject";
        Class clazz = Class.forName(name);

        String className = myObjectClass.getName();
        System.out.println(className);
        String simpleName = myObjectClass.getSimpleName();
        System.out.println(simpleName);

        int modifiers = myObjectClass.getModifiers();
        System.out.println(modifiers);

        Package packgae = myObjectClass.getPackage();
        System.out.println(packgae);

        Class superClass = myObjectClass.getSuperclass();
        System.out.println(superClass.getName());

        Class[] interfaces = myObjectClass.getInterfaces();
        System.out.println(interfaces);

        Constructor[] constructors = myObjectClass.getConstructors();
        System.out.println(constructors);

        Method[] methods = myObjectClass.getMethods();
        System.out.println(methods);

        Field[] fields = myObjectClass.getFields();
        System.out.println(fields);

        Annotation[] annotations = myObjectClass.getAnnotations();
        System.out.println(annotations);
    }
}
