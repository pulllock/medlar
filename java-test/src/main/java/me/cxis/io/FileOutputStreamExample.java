package me.cxis.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by cheng.xi on 2017-10-10 22:59.
 */
public class FileOutputStreamExample {
    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream("...");
        while (true) {
            int data = 111;
            outputStream.write(data);
        }

        // outputStream.close();

        //

        // OutputStream outputStream1 = new FileOutputStream("xxx", true);
        // OutputStream outputStream2 = new FileOutputStream("xxx", false);


    }
}
