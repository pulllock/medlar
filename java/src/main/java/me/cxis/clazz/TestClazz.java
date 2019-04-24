package me.cxis.clazz;

import java.io.Serializable;

public class TestClazz extends Thread implements Serializable {

    private String s;

    private static int m = 1;

    public String get(int id) {
        return "test-value";
    }


}
