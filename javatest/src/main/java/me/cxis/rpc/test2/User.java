package me.cxis.rpc.test2;

import java.io.Serializable;

/**
 * Created by cheng.xi on 17-2-24.
 * 对象需要实现Serializable接口
 */
public class User implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
