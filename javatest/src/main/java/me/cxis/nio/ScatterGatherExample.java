package me.cxis.nio;

import java.nio.ByteBuffer;

/**
 * Created by cheng.xi on 2017-10-11 22:13.
 */
public class ScatterGatherExample {
    public static void main(String[] args) {
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] bufferArray = {header, body};

        // channel.read(bufferArray);

        // channel.write(bufferArray);
    }
}
