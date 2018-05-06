package me.cxis.nio.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelUsageExample {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("http://www.cxis.me", 80));

        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buffer);

        String data = "new string to write to socket channel";

        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        byteBuffer.clear();
        byteBuffer.put(data.getBytes());

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {
            socketChannel.write(byteBuffer);
        }


        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://www.cxis.me", 80));

        while (!socketChannel.finishConnect()) {
            // wait
        }

        socketChannel.close();
    }
}
