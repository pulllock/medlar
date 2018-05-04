package me.cxis.reflection;

/**
 * Created by cheng.xi on 2017-10-14 09:27.
 */
public class MyObject {

    private String name;
    private int age;
    public int id;
    public String a;

    public MyObject(String name) {
        this.name = name;
    }

    public MyObject(int age) {
        this.age = age;
    }

    public MyObject() {
    }

    public MyObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
