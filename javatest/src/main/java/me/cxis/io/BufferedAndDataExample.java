package me.cxis.io;

import java.io.*;

/**
 * Created by cheng.xi on 2017-10-10 23:17.
 */
public class BufferedAndDataExample {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new BufferedInputStream(
                new FileInputStream("xxx")
        );

        inputStream = new BufferedInputStream(
                new FileInputStream(""), 8 * 1024
        );

        OutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream("xxx")
        );

        outputStream = new BufferedOutputStream(
                new FileOutputStream(""), 8 * 1024
        );

        DataInputStream dataInputStream = new DataInputStream(
                new FileInputStream("")
        );
        int aByte = dataInputStream.read();
        int aInt = dataInputStream.readInt();
        float aFloat = dataInputStream.readFloat();
        double aDouble = dataInputStream.readDouble();
        dataInputStream.close();

        DataOutputStream dataOutputStream = new DataOutputStream(
                new FileOutputStream("")
        );
        outputStream.write(45);
    }
}
