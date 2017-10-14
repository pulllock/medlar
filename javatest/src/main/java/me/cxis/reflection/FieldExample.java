package me.cxis.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by cheng.xi on 2017-10-14 09:48.
 */
public class FieldExample {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = MyObject.class;
        Field[] fields = clazz.getFields();
        Arrays.stream(fields)
                .forEach(s -> System.out.println(s));

        Field field = clazz.getField("a");
        System.out.println(field);
        System.out.println(field.getName());
        System.out.println(field.getType());

        MyObject instance = new MyObject();
        Object value = field.get(instance);
        System.out.println(value);
        field.set(instance, value);
    }
}
