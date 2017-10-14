package me.cxis.reflection;

import java.lang.reflect.Field;

/**
 * Created by cheng.xi on 2017-10-14 10:15.
 */
public class PrivateFieldsExample {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        MyObject instance = new MyObject(2);

        Class clazz = MyObject.class;
        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true);

        int value = (int) field.get(instance);
        System.out.println(value);
    }
}
