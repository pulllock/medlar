package me.cxis.gof.state_pattern.example4;

public enum State {
    UNLOCKED(1, "已开启"),
    LOCKED(2, "已关闭")
    ;

    private int state;
    private String desc;

    State(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public int getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }
}
