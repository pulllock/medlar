package me.cxis.json.gson.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserQuery implements Serializable {

    private static final long serialVersionUID = -3686422536752096137L;

    private Long id;

    private String name;

    private LocalDateTime birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
