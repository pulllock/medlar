package me.cxis.nio.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatteringReadExample {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("/nio-data.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] buffers = {header, body};

        channel.read(buffers);
    }
}
