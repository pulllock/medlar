package me.cxis.gof.state_pattern.example3;

public class Elevator {

    private State runState;
    private State closeState;
    private State stopState;
    private State openState;

    private State state;

    public Elevator() {
        runState = new RunState(this);
        closeState = new CloseState(this);
        stopState = new StopState(this);
        openState = new OpenState(this);
    }

    public void run() {
        state.run();
    }

    public void close() {
        state.close();
    }

    public void stop() {
        state.stop();
    }

    public void open() {
        state.open();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getRunState() {
        return runState;
    }

    public State getCloseState() {
        return closeState;
    }

    public State getStopState() {
        return stopState;
    }

    public State getOpenState() {
        return openState;
    }
}
