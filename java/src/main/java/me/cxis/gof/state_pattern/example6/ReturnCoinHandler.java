package me.cxis.gof.state_pattern.example6;

public class ReturnCoinHandler implements Handler {

    @Override
    public String execute() {
        return "退回硬币";
    }
}
