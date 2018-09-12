package me.cxis.gof.prototype_pattern.weekly_log_deep_clone;

import java.io.Serializable;

public class Attachment implements Serializable {

    private String name;

    public void download() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Attachment{" +
            "name='" + name + '\'' +
            '}';
    }
}
