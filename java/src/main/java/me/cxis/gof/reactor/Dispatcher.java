package me.cxis.gof.reactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reactor模式中负责event的分发和EventHandler的维护
 */
public class Dispatcher {

    // 用来维护不同的事件处理器
    Map<EventType, EventHandler> eventHandlerMap = new ConcurrentHashMap<>();

    Selector selector;

    public Dispatcher(Selector selector) {
        this.selector = selector;
    }

    // 注册EventHandler
    public void registerEventHandler(EventType type, EventHandler handler) {
        eventHandlerMap.put(type, handler);
    }

    public void removeEventHandler(EventType type) {
        eventHandlerMap.remove(type);
    }

    public void handleEvnets() {
        dispatch();
    }

    private void dispatch() {
        while (true) {
            List<Event> events = selector.select();

            for (Event event : events) {
                EventHandler handler = eventHandlerMap.get(event.getType());
                handler.handle(event);
            }
        }
    }
}
