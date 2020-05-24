package me.cxis.jmx.example3.example2;

public class User implements UserMBean {

    private String name;

    private int age;

    @Override
    public String getName() {
        System.out.println("getName: " + name);
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("setName: " + name);
        this.name = name;
    }

    @Override
    public int getAge() {
        System.out.println("getAge: " + age);
        return age;
    }

    @Override
    public void setAge(int age) {
        System.out.println("setAge: " + age);
        this.age = age;
    }
}
