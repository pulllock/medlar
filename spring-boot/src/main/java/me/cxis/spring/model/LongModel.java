package me.cxis.spring.model;

import java.io.Serializable;

public class LongModel implements Serializable {

    private static final long serialVersionUID = -4543850256110125483L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LongModel{" +
                "id=" + id +
                '}';
    }
}
