package me.cxis.sample.cloud.user.api.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -1947892706606550033L;

    private Long userId;

    private String name;

    private Integer age;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}