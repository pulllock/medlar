package me.cxis.gof.observer_pattern.example3;

public class ConcreteEventListener implements EventListener {

    @Override
    public void receiveEventFromEventSource(Event event) {
        System.out.println("receive event from event source: " + event);
    }
}
