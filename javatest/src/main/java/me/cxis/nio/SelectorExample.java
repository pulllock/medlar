package me.cxis.nio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by cheng.xi on 2017-10-11 22:24.
 */
public class SelectorExample {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

        int interestSet = key.interestOps();
        boolean isAccept = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        boolean isConnect = (interestSet & SelectionKey.OP_CONNECT) ==SelectionKey.OP_ACCEPT;

        int readySet = key.readyOps();

        key.isAcceptable();
        key.isConnectable();
        key.isReadable();
        key.isWritable();

        Channel channel1 = key.channel();
        Selector selector1 = key.selector();

        //key.attach(obj);
        Object attachedObj = key.attachment();

    }
}
