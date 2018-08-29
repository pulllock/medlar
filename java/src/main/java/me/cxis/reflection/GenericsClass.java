package me.cxis.reflection;

import java.util.List;

/**
 * Created by cheng.xi on 2017-10-14 14:22.
 */
public class GenericsClass {
    public List<String> strings = null;

    public List<String> getStrings() {
        return this.strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
}
