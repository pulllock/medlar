package me.cxis.gof.observer_pattern.example3;

public class ConcreteEventSource extends EventSource {

    public void eventHappened() {
        Event event = new Event();
        event.setKey("user.prefix");
        event.setValue("UserDev_");
        event.setEventType("Type_DateChanged");
        notifyListeners(event);
    }
}
