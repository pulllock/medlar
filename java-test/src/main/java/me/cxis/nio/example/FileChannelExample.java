package me.cxis.nio.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("/nio-data.txt", "rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = fileChannel.read(buffer);
        while (bytesRead != -1) {
            System.out.println("Read:" + bytesRead);
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }

            buffer.clear();
            bytesRead = fileChannel.read(buffer);
        }

        file.close();
    }
}
