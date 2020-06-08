package me.cxis.socket.example3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                byte[] dataFromClient = new byte[1024];
                int end = inputStream.read(dataFromClient);
                while (end != -1) {
                    end = inputStream.read(dataFromClient);
                }
                System.out.println(new String(dataFromClient));
                String data = new String(dataFromClient);
                System.out.println("客户端发来：" + data);

                String response = "收到：" + data;

                outputStream.write(response.getBytes(UTF_8));
                socket.shutdownOutput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
