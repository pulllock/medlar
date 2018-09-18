package me.cxis.gof.composite_pattern;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {

    private List<Component> list = new ArrayList<>();

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }

    @Override
    public Component getChild(int i) {
        return list.get(i);
    }

    @Override
    public void operation() {
        for (Component component : list) {
            component.operation();
        }
    }
}
