package me.cxis.groovy.model;

import java.io.Serializable;

public class SubmitParam implements Serializable {

    private static final long serialVersionUID = -6830390540653407446L;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SubmitParam{" +
                "age=" + age +
                '}';
    }
}
