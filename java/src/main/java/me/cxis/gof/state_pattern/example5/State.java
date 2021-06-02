package me.cxis.gof.state_pattern.example5;

public interface State {

    String insertCoin(Gate gate);

    String pass(Gate gate);
}
