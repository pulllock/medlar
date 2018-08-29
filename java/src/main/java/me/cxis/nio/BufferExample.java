package me.cxis.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by cheng.xi on 2017-10-11 21:53.
 */
public class BufferExample {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        CharBuffer charBuffer = CharBuffer.allocate(1024);

        // int bytesRead = inChannel.read(byteBuffer);

        //byteBuffer.put(127);

        // int bytesWritten = inChannel.write(buf);

        // byte aByte = buf.get();

        byteBuffer.mark();

        byteBuffer.reset();
    }
}
