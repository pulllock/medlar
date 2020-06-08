package me.cxis.socket.example3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8888);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            outputStream.write("我是客户端。。。".getBytes(UTF_8));
            socket.shutdownOutput();

            byte[] dataFromServer = new byte[1024];
            int end = inputStream.read(dataFromServer);
            while (end != -1) {
                end = inputStream.read(dataFromServer);
            }
            System.out.println(new String(dataFromServer));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
