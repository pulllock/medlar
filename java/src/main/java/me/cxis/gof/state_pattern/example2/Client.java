package me.cxis.gof.state_pattern.example2;

public class Client {

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        // 初始状态：开
        System.out.println("===电梯初始状态是：开 ===");
        elevator.setState(ElevatorState.OPEN);

        System.out.println("===尝试打开电梯===");
        elevator.open();

        elevator.setState(ElevatorState.OPEN);
        System.out.println("===尝试停止电梯===");
        elevator.stop();

        elevator.setState(ElevatorState.OPEN);
        System.out.println("===尝试关闭电梯===");
        elevator.close();

        elevator.setState(ElevatorState.OPEN);
        System.out.println("===尝试让电梯走===");
        elevator.run();


    }
}
