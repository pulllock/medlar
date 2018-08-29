package me.cxis.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by cheng.xi on 2017-10-10 22:02.
 */
public class PipExample {

    public static void main(String[] args) throws IOException {
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);

        Thread thread1 = new Thread(() -> {
            try {
                outputStream.write("Hello world,pipe".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                int data = inputStream.read();
                while (data != -1){
                    System.out.println((char)data);
                    data = inputStream.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
