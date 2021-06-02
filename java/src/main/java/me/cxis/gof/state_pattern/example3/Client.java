package me.cxis.gof.state_pattern.example3;

public class Client {

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.setState(elevator.getOpenState());
        elevator.close();
        elevator.run();
    }
}
