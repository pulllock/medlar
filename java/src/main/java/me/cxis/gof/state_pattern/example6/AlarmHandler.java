package me.cxis.gof.state_pattern.example6;

public class AlarmHandler implements Handler {

    @Override
    public String execute() {
        return "不能通过";
    }
}
