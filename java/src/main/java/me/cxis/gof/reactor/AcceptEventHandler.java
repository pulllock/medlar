package me.cxis.gof.reactor;

/**
 * Accept事件处理器
 */
public class AcceptEventHandler extends EventHandler {

    private Selector selector;

    public AcceptEventHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {
        // 处理Accept事件
        if (event.getType() == EventType.ACCEPT) {
            // 处理accept事件

            // 将事件状态改为下一个READ状态，并放入selector的缓冲队列中
            Event readEvent = new Event();
            readEvent.setSource(event.getSource());
            readEvent.setType(EventType.READ);

            selector.addEvent(readEvent);
        }
    }
}
