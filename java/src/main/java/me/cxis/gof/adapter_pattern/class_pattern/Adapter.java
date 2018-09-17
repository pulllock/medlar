package me.cxis.gof.adapter_pattern.class_pattern;

public class Adapter extends Adaptee implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}
