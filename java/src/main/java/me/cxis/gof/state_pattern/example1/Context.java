package me.cxis.gof.state_pattern.example1;

/**
 * 上下文，定义客户端感兴趣的接口
 */
public class Context {

    /**
     * 当前的状态
     */
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request() {state.handle(this);}

    @Override
    public String toString() {
        return "Context{" +
                "state=" + state +
                '}';
    }
}
