package me.cxis.gof.prototype_pattern.weekly_log_shallow_clone;

public class Attachment {

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
