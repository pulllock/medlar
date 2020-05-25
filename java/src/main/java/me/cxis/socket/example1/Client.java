package me.cxis.socket.example1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Client {

    private ScheduledExecutorService connectExecutor = Executors.newScheduledThreadPool(1, new NoneDaemonThreadFactory("test-socket-client-connect"));

    private Socket socket = null;
    private DataOutputStream outputStream = null;
    private DataInputStream inputStream = null;


    public static void main(String[] args) {
        new Client().start();

    }

    public void start() {
        connectExecutor.scheduleAtFixedRate(new Client.ConnectToServer(), 0, 5, TimeUnit.SECONDS);
    }

    class ConnectToServer implements Runnable {

        @Override
        public void run() {
            /*if (socket != null) {
                System.out.println("socket ready...");
                return;
            }*/
            try {
                if (socket == null) {
                    System.out.println("create socket...");
                    socket = new Socket("127.0.0.1", 8888);
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    inputStream = new DataInputStream(socket.getInputStream());
                }

                sendMsg(1, 4, "i am client");
            } catch (IOException e) {
                e.printStackTrace();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }


    private void sendMsg(int reqRes, int type, String msg) {
        try {
            System.out.println("send msg....");
            outputStream.writeInt(reqRes);
            outputStream.writeInt(type);
            byte[] data = msg.getBytes(UTF_8);
            outputStream.writeInt(data.length);
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
