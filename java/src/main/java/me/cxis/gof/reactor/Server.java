package me.cxis.gof.reactor;

public class Server {

    Selector selector = new Selector();
    Dispatcher dispatcher = new Dispatcher(selector);

    Acceptor acceptor;

    public Server(int port) {
        acceptor = new Acceptor(port, selector);
    }

    public void start() {
        dispatcher.registerEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
        new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
        dispatcher.handleEvnets();
    }
}
