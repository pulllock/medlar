package me.cxis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by cheng.xi on 2017-03-02 14:01.
 */
public class Test {
    public static void main(String[] args) {
        Socket socket = new Socket();
        String host = "";
        try {
            SocketAddress addr = new InetSocketAddress("10.21.40.55", 2181);
            socket.connect(addr, 1000);
            host = socket.getLocalAddress().getHostAddress();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Throwable e) {}
        }
        System.out.println(host);
    }
}
