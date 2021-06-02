package me.cxis.gof.state_pattern.example3;

public class CloseState extends AbstractState {

    public CloseState(Elevator elevator) {
        super(elevator);
    }

    @Override
    public void open() {
        System.out.println("正在打开");
        this.elevator.setState(elevator.getOpenState());
        System.out.println("已经打开");
    }

    @Override
    public void close() {
        System.out.println("已经是关闭状态");
    }

    @Override
    public void run() {
        System.out.println("准备走");
        this.elevator.setState(elevator.getRunState());
        System.out.println("正在走");
    }

    @Override
    public void stop() {
        System.out.println("已经停止");
    }
}
