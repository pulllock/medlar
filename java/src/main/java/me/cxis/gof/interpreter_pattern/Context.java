package me.cxis.gof.interpreter_pattern;

import java.util.HashMap;

public class Context {

    private HashMap<String, String> map = new HashMap<>();

    public void assign(String key, String value) {

    }

    public String lookup(String key) {
        return map.get(key);
    }
}
