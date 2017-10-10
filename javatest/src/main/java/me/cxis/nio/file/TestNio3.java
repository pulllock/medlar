package me.cxis.nio.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by cheng.xi on 22/12/2016.
 */
public class TestNio3 {

    public static void mapMemeryBuffer() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/xxx/深入理解Java虚拟机 JVM高级特性与最佳实践.pdf");
        FileOutputStream fileOutputStream = new FileOutputStream("/xxx/out.pdf");
        FileChannel inChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1 * 1024 * 1024);
        byte[] bytes = new byte[1 * 1024 * 1024];

        long start = System.currentTimeMillis();

        //inChannel.read(byteBuffer);
        MappedByteBuffer mappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
        System.out.println("fileChannel size：" + inChannel.size());

        long end = System.currentTimeMillis();
        System.out.println("Read time : " + (end - start));

        start = System.currentTimeMillis();

        //fileOutputStream.write(bytes);
        mappedByteBuffer.flip();
        end = System.currentTimeMillis();
        System.out.println("write time : " + (end - start));

        fileOutputStream.close();
        inChannel.close();
        fileInputStream.close();
    }

    public static void main(String[] args) throws IOException {
        mapMemeryBuffer();
    }
}
