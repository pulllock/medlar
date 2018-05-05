package me.cxis.nio.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferUsageExample {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("/nio-data.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }

            buffer.clear();
            bytesRead = channel.read(buffer);
        }
        file.close();
    }
}
