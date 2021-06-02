package me.cxis.gof.state_pattern.example3;

public class RunState extends AbstractState {

    public RunState(Elevator elevator) {
        super(elevator);
    }

    @Override
    public void open() {
        System.out.println("不能打开，需要等待停了才能开");
    }

    @Override
    public void close() {
        System.out.println("已经是关闭状态");
    }

    @Override
    public void run() {
        System.out.println("已经是运行状态");
    }

    @Override
    public void stop() {
        System.out.println("正在停止");
        System.out.println("已经停止");
    }
}
