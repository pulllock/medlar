package me.cxis.io;

import java.io.*;

/**
 * Created by cheng.xi on 2017-10-10 22:11.
 */
public class ByteArrayExample {

    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[1024];

        InputStream inputStream = new ByteArrayInputStream(bytes);

        int data = inputStream.read();
        while(data != -1) {
            data = inputStream.read();
        }

    }
}
