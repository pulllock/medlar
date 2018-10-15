package me.cxis.gof.memento_pattern;

public class Memento {

    private String state;

    public Memento(Originator originator) {
       state = originator.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
