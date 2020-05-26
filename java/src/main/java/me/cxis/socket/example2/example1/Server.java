package me.cxis.socket.example2.example1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(8888);
                    while (true) {
                        Socket socket = serverSocket.accept();
                        while (true) {
                            handleSocket(socket);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void handleSocket(Socket socket) throws IOException {
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        int reqRes = inputStream.readInt();
        int type = inputStream.readInt();
        int size = inputStream.readInt();
        byte[] data = new byte[size];
        inputStream.readFully(data);

        System.out.println(String.format("reqRes: %s, type: %s, data: %s", reqRes, type, new String(data)));

    }
}
