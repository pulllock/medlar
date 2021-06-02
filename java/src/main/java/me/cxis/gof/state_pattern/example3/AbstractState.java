package me.cxis.gof.state_pattern.example3;

public abstract class AbstractState implements State {

    protected Elevator elevator;

    public AbstractState(Elevator elevator) {
        this.elevator = elevator;
    }
}
