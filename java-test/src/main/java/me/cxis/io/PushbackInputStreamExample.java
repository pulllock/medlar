package me.cxis.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * Created by cheng.xi on 2017-10-10 23:30.
 */
public class PushbackInputStreamExample {
    public static void main(String[] args) throws IOException {
        PushbackInputStream inputStream = new PushbackInputStream(
                new FileInputStream("")
        );

        int data = inputStream.read();

        inputStream.unread(data);
    }
}
