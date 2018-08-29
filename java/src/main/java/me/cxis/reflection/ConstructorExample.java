package me.cxis.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by cheng.xi on 2017-10-14 09:35.
 */
public class ConstructorExample {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = MyObject.class;
        Constructor[] constructors = clazz.getConstructors();
        Arrays
                .stream(constructors)
                .forEach(s -> System.out.println(s));

        Constructor constructor = clazz.getConstructor(new Class[]{String.class});
        System.out.println(constructor);

        Class[] parameterTypes = constructor.getParameterTypes();
        Arrays.stream(parameterTypes)
                .forEach(s -> System.out.println(s));

        Constructor constructor1 =  MyObject.class.getConstructor(String.class);
        MyObject myObject = (MyObject)constructor1.newInstance("cccc");
        System.out.println(myObject.getName());
    }
}
