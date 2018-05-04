package me.cxis.io;

import java.io.*;

/**
 * Created by cheng.xi on 2017-10-10 22:22.
 */
public class StreamExample {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("/xxx/xx");
        int data = inputStream.read();
        while(data != -1) {
            data = inputStream.read();
        }

        //

        OutputStream outputStream = new FileOutputStream("/xxx/xxx");
        outputStream.write("hello".getBytes());
        outputStream.close();

        //

        InputStream inputStream1 = new BufferedInputStream(
                new FileInputStream("xxx")
        );
    }
}
