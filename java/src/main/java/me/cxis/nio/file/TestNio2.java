package me.cxis.nio.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by cheng.xi on 22/12/2016.
 */
public class TestNio2 {
    /**
     * 一次性把文件读入内存
     * @throws IOException
     */
    public static void testNio() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/xxx/1.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        //分配和文件尺寸等大的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)fileChannel.size());
        //整个文件全部读入缓冲区
        fileChannel.read(byteBuffer);
        //缓冲区当前位置重置为0
        byteBuffer.rewind();

        while(byteBuffer.hasRemaining()){
            System.out.print((char)byteBuffer.get());
        }

        fileInputStream.close();
        fileChannel.close();
    }

    /**
     * 手动指定缓冲区的大小
     */
    public static void testNio2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/xxx/1.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //缓冲区的position即为当前缓冲区中最后有效位置
        while(fileChannel.read(byteBuffer) != -1){
            //把缓冲区中当前位置恢复为0
            byteBuffer.flip();

            while(byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get());
            }

            byteBuffer.clear();
        }

        fileChannel.close();
        fileInputStream.close();
    }

    public static void testNio3() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/xxx/nio.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String word = "Hello java Nio";

        for(int i = 0; i < word.length(); i++){
            byteBuffer.putChar(word.charAt(i));
        }

        byteBuffer.flip();
        fileChannel.write(byteBuffer);

        fileChannel.close();
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        //testNio();
        //testNio2();
        testNio3();
    }
}
