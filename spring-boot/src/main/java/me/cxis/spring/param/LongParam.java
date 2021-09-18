package me.cxis.spring.param;

import java.io.Serializable;

public class LongParam implements Serializable {

    private static final long serialVersionUID = -8357268797061955484L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LongParam{" +
                "id=" + id +
                '}';
    }
}
