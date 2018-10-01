package me.cxis.gof.command_pattern;

public class ConcreteCommand extends Command {

    private Receiver receiver;

    @Override
    public void execute() {
        receiver.action();
    }
}
