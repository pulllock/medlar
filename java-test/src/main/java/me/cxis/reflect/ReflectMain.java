package me.cxis.reflect;

import java.lang.reflect.*;

/**
 * Created by cheng.xi on 6/12/16.
 */
public class ReflectMain {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException,
            NoSuchFieldException, NoSuchMethodException {

        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
        demo8();

    }

    //反射获得类的包名和类名
    public static void demo1(){

        Person person = new Person();
        System.out.println("包名:" + person.getClass().getPackage().getName()
                + ";类名:" + person.getClass().getName());
    }

    //验证所有类都是Class类的实例对象
    public static void demo2() throws ClassNotFoundException {
        Class<?> class1 = null;
        Class<?> class2 = null;

        class1 = Class.forName("cx.test.reflect.Person");
        System.out.println("Class.forName 包名:" + class1.getClass().getPackage().getName()
                + "; Class.forName 类名:" + class1.getClass().getName());

        class2 = Person.class;
        System.out.println("User.class 包名:" + class2.getClass().getPackage().getName()
                + "; User.class 类名:" + class2.getClass().getName());
    }

    //通过反射,用Class创建类对象
    public static void demo3() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> class1 = null;
        class1 = Class.forName("cx.test.reflect.Person");

        Person person = (Person) class1.newInstance();
        person.setAge(20);
        person.setName("demo3");
        System.out.println("demo3:" + person.getName() + ":" + person.getAge());
    }

    //通过反射得到一个类的构造函数,并创建带参实例对象
    public static void demo4() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> class1 = null;
        Person person1 = null;
        Person person2 = null;

        class1 = Class.forName("cx.test.reflect.Person");

        Constructor<?>[] constructors = class1.getConstructors();

        person1 = (Person) constructors[0].newInstance();
        person1.setAge(30);
        person1.setName("kkkk");

        person2 = (Person) constructors[1].newInstance(20,"ssss");
        System.out.println("Demo4:" + person1.getName() + ":" + person1.getAge()
                + ";" + person2.getName() + ":" + person2.getAge());
    }

    //通过反射操作成员变量,set,get
    public static void demo5() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> class1 = null;
        class1 = Class.forName("cx.test.reflect.Person");

        Object obj = class1.newInstance();

        Field nameField = class1.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(obj,"asd");

        System.out.println("修改后:" + nameField.get(obj));
    }

    //通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
    public static void demo6() throws ClassNotFoundException {

        Class<?> class1 = null;
        class1 = Class.forName("cx.test.reflect.SuperMan");

        Class<?> superClass = class1.getSuperclass();
        System.out.println("super class name: " + superClass.getName());


        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++){
            System.out.println("类成员:" + fields[i]);
        }

        Method[] methods = class1.getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            System.out.println("方法名:" + methods[i].getName());
            System.out.println("方法返回类型:" + methods[i].getReturnType());
            System.out.println("方法访问修饰符:" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("方法代码:" + methods[i]);
        }

        Class<?> interfaces[] = class1.getInterfaces();
        for(int i = 0; i < interfaces.length; i++){
            System.out.println("接口:" + interfaces[i].getName());
       }
    }

    //通过反射调用类方法
    public static void demo7() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> class1 = null;
        class1 = Class.forName("cx.test.reflect.SuperMan");

        Method method = class1.getMethod("fly");
        method.invoke(class1.newInstance());

        method = class1.getMethod("walk",int.class);
        method.invoke(class1.newInstance(),100);
    }

    //通过反射得到类加载器信息
    public static void demo8() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("cx.test.reflect.SuperMan");

        System.out.println("类加载器:" + class1.getClassLoader().getClass().getName());
    }

}
