package me.cxis.nio.example;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorUsageExample {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        Selector selector = Selector.open();

        channel.configureBlocking(false);

        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = keys.iterator();

        while (keyIterator.hasNext()) {
            SelectionKey selectionKey = keyIterator.next();

            if (selectionKey.isAcceptable()) {

            } else if (selectionKey.isConnectable()) {

            } else if (selectionKey.isReadable()) {

            } else if (selectionKey.isWritable()) {

            }

            keyIterator.remove();
        }
    }
}
