package me.cxis.gof.iterator_pattern;

public class ConcreteIterator implements Iterator {

    private ConcreteAggregate objects;

    private int cursor;

    public ConcreteIterator(ConcreteAggregate objects) {
        this.objects = objects;
    }

    @Override
    public void first() {

    }

    @Override
    public void next() {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object currentItem() {
        return null;
    }
}
