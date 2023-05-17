package me.cxis.spring.example.responsibility.chain.example1;

public abstract class Subject {

    private Subject successor;

    protected abstract void handle();

    public void execute() {
        handle();

        if (successor != null) {
            successor.execute();
        }
    }

    public Subject getSuccessor() {
        return successor;
    }

    public void setSuccessor(Subject successor) {
        this.successor = successor;
    }
}
