package me.cxis.gof.state_pattern.example4;

public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate(State.UNLOCKED);
        System.out.println(gate.execute(Action.INSERT_COIN));

        System.out.println("======");

        gate = new Gate(State.LOCKED);
        System.out.println(gate.execute(Action.INSERT_COIN));

        System.out.println("======");

        gate = new Gate(State.UNLOCKED);
        System.out.println(gate.execute(Action.PASS));

        System.out.println("======");

        gate = new Gate(State.LOCKED);
        System.out.println(gate.execute(Action.PASS));
    }
}
