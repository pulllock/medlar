package me.cxis.gof.state_pattern.example6;

public class GateStateConfig {

    private State currentState;

    private State nextState;

    private Action action;

    private Handler handler;

    public static GateStateConfig builder() {
        return new GateStateConfig();
    }

    public GateStateConfig from(State state) {
        this.currentState = state;
        return this;
    }

    public GateStateConfig to(State state) {
        this.nextState = state;
        return this;
    }

    public GateStateConfig action(Action action) {
        this.action = action;
        return this;
    }

    public GateStateConfig handle(Handler handler) {
        this.handler = handler;
        return this;
    }

    public GateStateConfig build() {
        return this;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
