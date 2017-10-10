package me.cxis.algorithms;

import java.lang.reflect.Method;

/**
 * Created by cx on 7/20/16.
 */
public class PrintClassInfo {

    public static void printClassInfo(Object obj){
        Class clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            System.out.println("Class Name:" + clazz.getName() + ";MethodName:" + methods[i].getName());
        }
    }
    class A{
        public void a(){}
        public void b(){}
        public void c(){}
    }

    public static void main(String[] args) {
        PrintClassInfo printClassInfo = new PrintClassInfo();
        PrintClassInfo.A a = printClassInfo.new A();
        printClassInfo(a);
    }
}
