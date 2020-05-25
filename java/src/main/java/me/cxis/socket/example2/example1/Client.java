package me.cxis.socket.example2.example1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Client {

    private ScheduledExecutorService connectExecutor = Executors.newScheduledThreadPool(1, new NoneDaemonThreadFactory("test-socket-client-connect"));

    private ScheduledExecutorService sendMsgExecutor = Executors.newScheduledThreadPool(1, new NoneDaemonThreadFactory("test-socket-client-sent-msg"));

    private Socket socket = null;
    private DataOutputStream outputStream = null;

    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new Client().start();
    }

    public void start() {
        connectExecutor.scheduleAtFixedRate(new Client.ConnectToServer(), 0, 5, TimeUnit.SECONDS);
        sendMsgExecutor.scheduleAtFixedRate(new Client.SendMsgTask(), 5, 10, TimeUnit.SECONDS);
    }

    class ConnectToServer implements Runnable {

        @Override
        public void run() {
            if (socket != null) {
                System.out.println("socket ready...");
                return;
            }
            try {
                System.out.println("connect to server...");
                socket = new Socket("127.0.0.1", 8888);
                outputStream = new DataOutputStream(socket.getOutputStream());
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

    public class SendMsgTask implements Runnable {

        @Override
        public void run() {
            System.out.println("ask time...");
            sendMsg(1, 2, String.format("Client time: %s, tell me server time!", System.currentTimeMillis()));
        }
    }

    private void sendMsg(int reqRes, int type, String msg) {
        lock.lock();
        try {
            outputStream.writeInt(reqRes);
            outputStream.writeInt(type);
            byte[] data = msg.getBytes(UTF_8);
            outputStream.writeInt(data.length);
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
