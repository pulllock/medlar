package me.cxis.nio.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by cheng.xi on 21/12/2016.
 */
public class TestNio {
    public static void readNio(){
        Path file = Paths.get("/xxx/1.txt");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file.toFile());

            FileChannel fileChannel = fileInputStream.getChannel();

            int capacity = 100;//容量100字节
            //容量为100字节的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
            System.out.println("缓冲区的limit=" + byteBuffer.limit());
            System.out.println("缓冲区的capacity=" + byteBuffer.capacity());
            System.out.println("缓冲区的position=" + byteBuffer.position());

            int length = -1;
        while((length = fileChannel.read(byteBuffer)) != -1){
            byte[] bytes = byteBuffer.array();
            System.out.write(bytes,0,length);
            System.out.println();
            System.out.println("缓冲区的limit:" + byteBuffer.limit() + ";缓冲区的capacity:" + byteBuffer.capacity() + ";缓冲区的position:" + byteBuffer.position());
            byteBuffer.clear();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeNio(){
        Path file = Paths.get("/xxx/out.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file.toFile());
            FileChannel fileChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = Charset.forName("utf-8").encode("我");
            System.out.println("缓冲区的limit=" + byteBuffer.limit());
            System.out.println("缓冲区的capacity=" + byteBuffer.capacity());
            System.out.println("缓冲区的position=" + byteBuffer.position());

            int length = 0;

            while((length = fileChannel.write(byteBuffer)) != 0){
                System.out.println("写入长度：" + length);
                System.out.println("缓冲区的limit:" + byteBuffer.limit() + ";缓冲区的capacity:" + byteBuffer.capacity() + ";缓冲区的position:" + byteBuffer.position());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readAndWriteNio(){
        Path srcFile = Paths.get("/xxx/1.txt");
        Path destFile = Paths.get("/xxx/out.txt");

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(srcFile.toFile());
            fileOutputStream = new FileOutputStream(destFile.toFile());

            FileChannel inChannel = fileInputStream.getChannel();
            FileChannel outChannel = fileOutputStream.getChannel();

            int capacity = 100;
            ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
            System.out.println("缓冲区的limit=" + byteBuffer.limit());
            System.out.println("缓冲区的capacity=" + byteBuffer.capacity());
            System.out.println("缓冲区的position=" + byteBuffer.position());

            int length = -1;
            while((length = inChannel.read(byteBuffer)) != -1){
                byteBuffer.flip();

                int outLength = 0;
                while((outLength = outChannel.write(byteBuffer)) != 0){
                    System.out.println("读：" + length + "；写" + outLength);
                }
                byteBuffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        //readNio();
        //writeNio();
        readAndWriteNio();
    }

}
