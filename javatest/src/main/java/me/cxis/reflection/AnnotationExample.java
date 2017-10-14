package me.cxis.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by cheng.xi on 2017-10-14 10:36.
 */
public class AnnotationExample {

    public static void main(String[] args) throws NoSuchMethodException {
        Class clazz = AnnotationClass.class;

        Annotation[] annotations = clazz.getAnnotations();
        Arrays.stream(annotations)
                .forEach(s -> System.out.println(s));
        Arrays.stream(annotations)
                .forEach(annotation -> {
                    if (annotation instanceof MyAnnotation) {
                        MyAnnotation myAnnotation = (MyAnnotation) annotation;
                        System.out.println(myAnnotation.name());
                        System.out.println(myAnnotation.value());
                    }
                });

        Annotation annotation = clazz.getAnnotation(MyAnnotation.class);
        if (annotation instanceof MyAnnotation) {
            MyAnnotation myAnnotation = (MyAnnotation) annotation;
            System.out.println(myAnnotation.name());
            System.out.println(myAnnotation.value());
        }

        Method method = clazz.getMethod("something");
        Annotation[] annotations1 = method.getDeclaredAnnotations();

        Arrays.stream(annotations1)
                .forEach(annotation1 -> {
                    if (annotation1 instanceof MyAnnotation) {
                        MyAnnotation myAnnotation = (MyAnnotation) annotation1;
                        System.out.println(myAnnotation.name());
                        System.out.println(myAnnotation.value());
                    }
                });

        Annotation annotation1 = method.getAnnotation(MyAnnotation.class);
        if (annotation1 instanceof MyAnnotation) {
            MyAnnotation myAnnotation = (MyAnnotation) annotation1;
            System.out.println(myAnnotation.name());
            System.out.println(myAnnotation.value());
        }

        Method method1 = clazz.getMethod("somethingElse", String.class);
        Annotation[][] annotation2 = method1.getParameterAnnotations();
        Class[] parameterTypes = method1.getParameterTypes();

        int i = 0;
        for (Annotation[] annotations2: annotation2) {
            Class patameterType = parameterTypes[i++];
            for (Annotation annotation3 : annotations2) {
                if (annotation3 instanceof MyAnnotation) {
                    MyAnnotation myAnnotation = (MyAnnotation) annotation3;
                    System.out.println(patameterType.getName());
                    System.out.println(myAnnotation.name());
                    System.out.println(myAnnotation.value());
                }
            }
        }

    }
}
