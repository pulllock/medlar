package me.cxis.gof.visitor_pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectStructure {

    private List<Element> elements = new ArrayList<>();

    public void accept(Visitor visitor) {
        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(visitor);
        }
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void removeElement(Element element) {
        elements.remove(element);
    }
}
