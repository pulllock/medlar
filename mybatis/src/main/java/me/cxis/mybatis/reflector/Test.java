package me.cxis.mybatis.reflector;

import org.apache.ibatis.reflection.TypeParameterResolver;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Test {

    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> clazz = Level3Mapper.class;
        Method method = clazz.getMethod("pageQueryUser");
        Type result = TypeParameterResolver.resolveReturnType(method, clazz);
        ParameterizedType paramTypeOuter = (ParameterizedType) result;
        ParameterizedType paramTypeInner = (ParameterizedType) paramTypeOuter.getActualTypeArguments()[0];
        System.out.println(paramTypeInner.getActualTypeArguments()[0]);
    }
}
