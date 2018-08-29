package me.cxis.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cheng.xi on 2017-10-14 14:23.
 */
public class GenericsExample {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Method method = GenericsClass.class.getMethod("getStrings");
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] types = type.getActualTypeArguments();
            Arrays.stream(types)
                    .forEach(s -> {
                        Class typeArgClass = (Class) s;
                        System.out.println(typeArgClass);
                    });

        }

        method = GenericsClass.class.getMethod("setStrings", List.class);
        Type[] types = method.getGenericParameterTypes();
        Arrays.stream(types)
                .forEach(s -> {
                    if (s instanceof ParameterizedType) {
                        ParameterizedType type = (ParameterizedType)s;
                        Type[] types1 = type.getActualTypeArguments();
                        Arrays.stream(types1).forEach(type1 -> {
                            System.out.println((Class)type1);
                        });
                    }
                });

        Field field = GenericsClass.class.getField("strings");
        Type fieldType = field.getGenericType();
        if (fieldType instanceof ParameterizedType) {
            ParameterizedType atype = (ParameterizedType)fieldType;
            Type[] fieldArgTypes = atype.getActualTypeArguments();
            for (Type type : fieldArgTypes) {
                Class fieldArgClass = (Class)type;
                System.out.println(fieldArgClass);
            }
        }
    }
}
