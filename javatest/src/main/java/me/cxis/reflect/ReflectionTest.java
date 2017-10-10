package me.cxis.reflect;

import java.lang.annotation.Annotation;

/**
 * Created by cheng.xi on 15/12/2016.
 */
public class ReflectionTest {

    public static void resloveClass(String classFullName) throws ClassNotFoundException {
        Class clazz = Class.forName(classFullName);

        Package clazzPackage = clazz.getPackage();
        System.out.println("包信息:                 " + clazzPackage);
        System.out.println("包名:                  " + clazzPackage.getName());
        System.out.println("ImplementationTitle:   " + clazzPackage.getImplementationTitle());
        System.out.println("ImplementationVendor:  " + clazzPackage.getImplementationVendor());
        System.out.println("ImplementationVersion: " + clazzPackage.getImplementationVersion());
        System.out.println("SpecificationTitle:    " + clazzPackage.getSpecificationTitle());
        System.out.println("SpecificationVendor:   " + clazzPackage.getSpecificationVendor());
        System.out.println("SpecificationVersion:  " + clazzPackage.getSpecificationVersion());


        Annotation[] clazzPackageAnnotations = clazzPackage.getAnnotations();
        if(clazzPackageAnnotations.length > 0){
            for(Annotation annotation : clazzPackageAnnotations){
                System.out.println("annotationType:    " + annotation.annotationType());
            }
        }else {
            System.out.println(clazzPackage.getName() + "getAnnotations为0");
        }

        System.out.println(":");
        System.out.println(":");
        System.out.println(":");
        System.out.println(":");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        resloveClass("java.lang.Integer");
    }

}
