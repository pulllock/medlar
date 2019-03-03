package me.cxis.spring.example.responsibility.chain.example2;

public abstract class Subject {

    protected abstract void handle();

    public void execute(SubjectChain successor) {
        handle();
        successor.process();
    }
}
