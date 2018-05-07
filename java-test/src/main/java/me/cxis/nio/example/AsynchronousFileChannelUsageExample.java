package me.cxis.nio.example;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousFileChannelUsageExample {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("test.txt");

        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(48);

        // 1. read
        Future<Integer> operation = channel.read(buffer, 0);

        while (!operation.isDone()) {

        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        buffer.clear();

        // 2. read
        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

        // 1. write
        channel.write(buffer, 0);

        // 2. write
        channel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {

            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }
}
