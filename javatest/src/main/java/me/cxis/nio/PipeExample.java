package me.cxis.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by cheng.xi on 2017-10-13 22:39.
 */
public class PipeExample {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();

        String data = "new string to write to file";
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(data.getBytes());
        buffer.flip();

        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf = ByteBuffer.allocate(48);

        int byteRead = sourceChannel.read(buf);
    }
}
