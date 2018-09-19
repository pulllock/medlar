package me.cxis.gof.composite_pattern.safe_composite;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {

    private List<Component> list = new ArrayList<>();

    @Override
    public void operation() {
        for (Component component : list) {
            component.operation();
        }
    }

    public void add(Component component) {
        list.add(component);
    }

    public void remove(Component component) {
        list.remove(component);
    }

    public Component getChild(int i) {
        return list.get(i);
    }
}
