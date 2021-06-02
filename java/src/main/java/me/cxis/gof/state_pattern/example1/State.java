package me.cxis.gof.state_pattern.example1;

/**
 * 状态接口
 */
public interface State {

    /**
     * 状态对应的处理
     * @param context 上下文
     */
    void handle(Context context);
}
