package me.cxis.gof.mediator_pattern;

import java.util.List;

public abstract class Mediator {

    protected List<Colleague> colleagues;

    public void register(Colleague colleague) {
        colleagues.add(colleague);
    }

    public abstract void operation();
}
