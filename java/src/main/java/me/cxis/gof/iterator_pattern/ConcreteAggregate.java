package me.cxis.gof.iterator_pattern;

public class ConcreteAggregate implements Aggregate{
    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }
}
