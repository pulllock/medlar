package me.cxis.gof.interpreter_pattern.robot;

public class Client {

    public static void main(String[] args) {
        String instruction = "up move 2 and down run 10 and left move 5";
        InstructionHandler handler = new InstructionHandler();
        handler.handle(instruction);
        System.out.println(handler.output());
    }
}
