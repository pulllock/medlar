package me.cxis.gof.state_pattern.example4;

import static me.cxis.gof.state_pattern.example4.State.LOCKED;
import static me.cxis.gof.state_pattern.example4.State.UNLOCKED;

public class Gate {

    private State state;

    public Gate(State state) {
        this.state = state;
    }

    public String execute(Action action) {
        // 当前状态是已打开
        if (state == UNLOCKED) {
            switch (action) {
                case PASS: {
                    // 已打开状态下通过，需要关闭
                    state = UNLOCKED;
                    return "已通过，已关闭";
                }
                case INSERT_COIN: {
                    // 已打开状态下投币，需要退回
                    return "已打开，退回硬币";
                }
            }
        }

        // 当前状态是已关闭
        if (state == LOCKED) {
            switch (action) {
                case PASS: {
                    // 已关闭状态下通过，警告
                    return "已关闭，不能通过";
                }
                case INSERT_COIN: {
                    // 已关闭状态下，投币后开启
                    state = UNLOCKED;
                    return "已投币，已开启";
                }
            }
        }

        return null;
    }
}
