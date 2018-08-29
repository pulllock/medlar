package me.cxis.io;

import java.io.*;

/**
 * Created by cheng.xi on 2017-10-10 22:57.
 */
public class FileInputStreamExample {
    public static void main(String[] args) throws IOException {
        InputStream inputStream  = new FileInputStream("");

        int data = inputStream.read();
        while (data != -1) {
            data = inputStream.read();
        }

        inputStream.close();

        //

        File file = new File("xxx");
        InputStream inputStream1 = new FileInputStream(file);
        //...
    }
}
