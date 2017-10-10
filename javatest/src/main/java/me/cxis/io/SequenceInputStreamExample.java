package me.cxis.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.SequenceInputStream;

/**
 * Created by cheng.xi on 2017-10-10 23:32.
 */
public class SequenceInputStreamExample {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("");
        InputStream inputStream1 = new FileInputStream("");

        InputStream combined = new SequenceInputStream(inputStream1, inputStream);

    }
}
