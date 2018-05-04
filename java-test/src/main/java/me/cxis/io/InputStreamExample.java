package me.cxis.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cheng.xi on 2017-10-10 22:42.
 */
public class InputStreamExample {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("xxx");
        int data = inputStream.read();
        while(data != -1) {
            data = inputStream.read();
        }

        inputStream.close();

        //

        try(InputStream inputStream1 = new FileInputStream("xxx")){
            int data1 = inputStream1.read();
            while(data1 != -1) {
                data1 = inputStream1.read();
            }
        }

        //

        InputStream inputStream1 = new FileInputStream("xxx");
        byte[] datas = new byte[1024];
        int bytesRead = inputStream1.read(datas);
        while(bytesRead != -1) {
            bytesRead = inputStream.read(datas);
        }

        inputStream1.close();
    }
}
