package me.cxis.gof.state_pattern.example1;

/**
 * 具体状态实现类
 */
public class ConcreteState2 implements State {

    @Override
    public void handle(Context context) {
        System.out.println(String.format("%s handle, context: %s", this.getClass().getName(), context));
    }
}
