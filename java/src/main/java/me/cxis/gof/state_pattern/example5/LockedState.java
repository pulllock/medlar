package me.cxis.gof.state_pattern.example5;

public class LockedState implements State {

    @Override
    public String insertCoin(Gate gate) {
        return gate.open();
    }

    @Override
    public String pass(Gate gate) {
        return gate.alarm();
    }
}
