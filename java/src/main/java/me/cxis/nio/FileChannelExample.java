package me.cxis.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by cheng.xi on 2017-10-11 21:42.
 */
public class FileChannelExample {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("xxxx", "rw");
        FileChannel inChannel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buffer);
        while (bytesRead != -1) {
            System.out.println(bytesRead);
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.println((char)buffer.get());
            }

            buffer.clear();
            bytesRead = inChannel.read(buffer);
        }

        file.close();
    }
}
