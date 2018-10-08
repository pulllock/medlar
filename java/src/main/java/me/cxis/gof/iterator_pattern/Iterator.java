package me.cxis.gof.iterator_pattern;

public interface Iterator {

    void first();

    void next();

    boolean hasNext();

    Object currentItem();
}
