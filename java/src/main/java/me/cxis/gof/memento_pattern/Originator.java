package me.cxis.gof.memento_pattern;

// 类使用默认修饰符，保证只有包内可见
class Originator {

    private String state;

    public Originator(String state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(this);
    }

    public void restoreMemento(Memento memento) {
        state = memento.getState();
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
