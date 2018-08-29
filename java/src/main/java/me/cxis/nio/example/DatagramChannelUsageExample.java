package me.cxis.nio.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelUsageExample {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        channel.receive(buffer);

        String data = "new string to write to datagram channel";
        ByteBuffer buffer1 = ByteBuffer.allocate(48);
        buffer1.clear();
        buffer1.put(data.getBytes());
        buffer1.flip();

        int bytesSent = channel.send(buffer1, new InetSocketAddress("http://www.cxis.me", 80));
    }
}
