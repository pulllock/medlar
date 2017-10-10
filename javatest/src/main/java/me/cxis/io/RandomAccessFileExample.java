package me.cxis.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by cheng.xi on 2017-10-10 23:03.
 */
public class RandomAccessFileExample {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("xxxx", "rw");
        //对某个位置读写之前，先把文件指针指向该位置
        file.seek(200);
        // 获取当前文件指针的位置
        long pointer = file.getFilePointer();
        //读取
        int bytea = file.read();
        //写入
        file.write("hello".getBytes());
        file.close();
    }
}
