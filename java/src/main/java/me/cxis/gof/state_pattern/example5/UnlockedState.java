package me.cxis.gof.state_pattern.example5;

public class UnlockedState implements State {

    @Override
    public String insertCoin(Gate gate) {
        return gate.returnCoin();
    }

    @Override
    public String pass(Gate gate) {
        return gate.close();
    }
}
