package me.cxis.gof.observer_pattern.example3;

import java.util.ArrayList;
import java.util.List;

public abstract class EventSource {

    protected List<EventListener> eventListeners = new ArrayList<>();

    public void addEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public void removeEventListener(EventListener eventListener) {
        eventListeners.remove(eventListener);
    }

    public void notifyListeners(Event event) {
        eventListeners.forEach(eventListener -> eventListener.receiveEventFromEventSource(event));
    }
}
