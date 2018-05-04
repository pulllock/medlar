package me.cxis.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * Created by cheng.xi on 2017-10-13 23:15.
 */
public class AsynchronousFileChannelExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("xxxx");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        Future<Integer> operation = fileChannel.read(null, 0);

        while (!operation.isDone());

        fileChannel.read(null, 0, null, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {

            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }
}
