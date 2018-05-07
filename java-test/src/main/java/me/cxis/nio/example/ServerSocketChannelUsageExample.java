package me.cxis.nio.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelUsageExample {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));
        channel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = channel.accept();
            if (socketChannel != null) {
                // do....
            }
        }

        // channel.close();
    }
}
