package me.cxis.gof.state_pattern.example3;

public interface State {

    void open();

    void close();

    void run();

    void stop();
}
