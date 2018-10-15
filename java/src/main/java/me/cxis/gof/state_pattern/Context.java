package me.cxis.gof.state_pattern;

public class Context {

    private State state;

    private int value;

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        state.handle();
    }
}
