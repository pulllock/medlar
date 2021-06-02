package me.cxis.gof.state_pattern.example2;

/**
 * 电梯
 */
public class Elevator {

    /**
     * 电梯状态
     */
    private ElevatorState state;

    /**
     * 电梯正在运行状态
     */
    public void run() {
        System.out.println("电梯正在运行状态");
        switch (state) {
            case OPEN:
                System.out.println("不能打开，需要等待停了才能开");
                break;
            case CLOSE:
                System.out.println("已经是关闭状态");
                break;
            case RUN:
                System.out.println("已经是运行状态");
                break;
            case STOP:
                System.out.println("正在停止");
                state = ElevatorState.STOP;
                System.out.println("已经停止");
                break;
            default:
                System.out.println("错误的状态");
        }
    }

    /**
     * 电梯打开状态
     */
    public void open() {
        System.out.println("电梯打开状态");
        switch (state) {
            case OPEN:
                System.out.println("已经是打开状态");
                break;
            case CLOSE:
                System.out.println("正在关闭");
                state = ElevatorState.CLOSE;
                System.out.println("已经关闭");
                break;
            case RUN:
                System.out.println("还不能走，需要先关闭");
                break;
            case STOP:
                System.out.println("已经停止");
                break;
            default:
                System.out.println("错误的状态");
        }
    }

    /**
     * 电梯关闭状态
     */
    public void close() {
        System.out.println("电梯关闭状态");
        switch (state) {
            case OPEN:
                System.out.println("正在打开");
                state = ElevatorState.OPEN;
                System.out.println("已经打开");
                break;
            case CLOSE:
                System.out.println("已经关闭");
                break;
            case RUN:
                System.out.println("准备走");
                state = ElevatorState.RUN;
                System.out.println("正在走");
                break;
            case STOP:
                System.out.println("已经停止");
                break;
            default:
                System.out.println("错误的状态");
        }
    }

    /**
     * 电梯停止状态
     */
    public void stop() {
        System.out.println("电梯停止状态");
        switch (state) {
            case OPEN:
                System.out.println("正在打开");
                state = ElevatorState.OPEN;
                System.out.println("已经打开");
                break;
            case CLOSE:
                System.out.println("正在关闭");
                state = ElevatorState.CLOSE;
                System.out.println("已经关闭");
                break;
            case RUN:
                System.out.println("准备走");
                state = ElevatorState.RUN;
                System.out.println("正在走");
                break;
            case STOP:
                System.out.println("已经停止");
                break;
            default:
                System.out.println("错误的状态");
        }
    }


    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }
}
