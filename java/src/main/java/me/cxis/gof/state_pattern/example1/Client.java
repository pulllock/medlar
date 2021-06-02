package me.cxis.gof.state_pattern.example1;

public class Client {

    public static void main(String[] args) {
        // 具体状态1
        State state1 = new ConcreteState1();

        // 上下文
        Context context = new Context(state1);

        context.request();

        // 具体状态2
        State state2 = new ConcreteState2();
        context.setState(state2);
        context.request();
    }
}
