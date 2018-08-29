package me.cxis.nio.example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipUsageExample {

    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();

        Pipe.SinkChannel sinkChannel = pipe.sink();

        String data = "new string to write to sink channel";

        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(data.getBytes());

        buffer.flip();

        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();

        ByteBuffer buffer1 = ByteBuffer.allocate(48);
        int bytesRead = sourceChannel.read(buffer1);
    }
}
