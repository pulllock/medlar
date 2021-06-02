package me.cxis.gof.state_pattern.example2;

/**
 * 电梯状态
 */
public enum ElevatorState {
    OPEN       (1, "开"),
    CLOSE      (2, "关"),
    RUN        (3, "走"),
    STOP       (4, "停")
    ;

    private int state;

    private String desc;

    ElevatorState(int state, String desc) {
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
