package me.cxis.gof.state_pattern.example6;

public class OpenHandler implements Handler {

    @Override
    public String execute() {
        return "已打开";
    }
}
