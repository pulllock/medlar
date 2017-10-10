package me.cxis.io;

import java.io.*;

/**
 * Created by cheng.xi on 2017-10-10 22:26.
 */
public class ReaderExample {

    public static void main(String[] args) throws IOException {

        Reader reader = new FileReader("xxx");
        int data = reader.read();
        while(data != -1) {
            char dataChar = (char)data;
            data = reader.read();
        }

        //

        Reader reader1 = new InputStreamReader(
                new FileInputStream("xxx")
        );

        //

        Writer writer = new FileWriter("xxxx");
        writer.write("hello");
        writer.close();

        //

        Writer writer1 = new OutputStreamWriter(
                new FileOutputStream("xxxxx")
        );
    }
}
