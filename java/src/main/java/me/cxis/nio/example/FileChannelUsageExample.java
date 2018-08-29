package me.cxis.nio.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelUsageExample {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = channel.read(buffer);

        String data = "new string to write to file";

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(data.getBytes());
        buf.flip();

        while (buf.hasRemaining()) {
            channel.write(buf);
        }

        channel.close();

        long position = channel.position();

        channel.position(position + 123);

        long fileSize = channel.size();

        channel.truncate(1024);

        channel.force(true);
    }
}
