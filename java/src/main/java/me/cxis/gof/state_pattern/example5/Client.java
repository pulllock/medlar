package me.cxis.gof.state_pattern.example5;

public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate(new UnlockedState());
        System.out.println(gate.execute(Action.INSERT_COIN));

        System.out.println("======");

        gate = new Gate(new UnlockedState());
        System.out.println(gate.execute(Action.PASS));

        System.out.println("======");

        gate = new Gate(new LockedState());
        System.out.println(gate.execute(Action.INSERT_COIN));

        System.out.println("======");

        gate = new Gate(new LockedState());
        System.out.println(gate.execute(Action.PASS));
    }
}
