package me.cxis.gof.state_pattern.example5;

public class Gate {

    private State locked = new LockedState();
    private State unlocked = new UnlockedState();

    private State state;

    public Gate(State state) {
        this.state = state;
    }

    public String execute(Action action) {
        if (action == Action.PASS) {
            return state.pass(this);
        }

        return state.insertCoin(this);
    }

    public String open() {
        setState(locked);
        return "已投币，已打开";
    }

    public String alarm() {
        setState(locked);
        return "已关闭，不能通过";
    }

    public String close() {
        setState(locked);
        return "已通过，已关闭";
    }

    public String returnCoin() {
        setState(unlocked);
        return "已打开，退回硬币";
    }

    public void setState(State state) {
        this.state = state;
    }
}
