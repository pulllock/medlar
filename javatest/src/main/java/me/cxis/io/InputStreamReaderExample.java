package me.cxis.io;

import java.io.*;

/**
 * Created by cheng.xi on 2017-10-10 23:25.
 */
public class InputStreamReaderExample {
    public static void main(String[] args) throws IOException {

        Reader reader = new InputStreamReader(
                new FileInputStream("xxx"),
                "utf-8"
        );

        int data = reader.read();
        while (data != -1) {
            char theChar = (char) data;
            data = reader.read();
        }

        reader.close();
    }
}
