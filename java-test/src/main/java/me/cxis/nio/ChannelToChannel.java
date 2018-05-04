package me.cxis.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by cheng.xi on 2017-10-11 22:18.
 */
public class ChannelToChannel {
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("xxx", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("yyy", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

        fromChannel.transferTo(position, count, toChannel);
    }
}
