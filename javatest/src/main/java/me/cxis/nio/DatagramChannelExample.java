package me.cxis.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by cheng.xi on 2017-10-13 22:30.
 */
public class DatagramChannelExample {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);


        String data = "new String to write to file ";
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(data.getBytes());
        buffer.flip();

        int bytesSent = channel.send(buffer, new InetSocketAddress("xxxx", 80));

    }
}
