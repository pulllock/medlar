package me.cxis.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by cheng.xi on 2017-10-10 22:52.
 */
public class OutputStreamExample {

    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream("xxx");
        while(true) {
            outputStream.write("".getBytes());
        }
    }
}
