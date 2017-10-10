package me.cxis.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by cx on 8/30/16.
 */
public class MyClient {

    public static void main(String args[]) throws IOException {
        int port = 8080;

        // 发送接收缓冲区
        ByteBuffer send = ByteBuffer.wrap("data come from client".getBytes());
        ByteBuffer receive = ByteBuffer.allocate(1024);

        SocketChannel sc = SocketChannel.open();
        Selector selector = Selector.open();
        // 注册为非阻塞通道
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost", port));
        sc.register(selector, SelectionKey.OP_CONNECT|SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        while (true){
            // 选择
            if (selector.select() == 0){
                continue;
            }
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                // 必须由程序员手动操作
                it.remove();
                sc = (SocketChannel) key.channel();
                if (key.isConnectable()){
                    if (sc.isConnectionPending()){
                        // 结束连接，以完成整个连接过程
                        sc.finishConnect();
                        System.out.println("connect completely");
                        sc.write(send);
                    }

                }else if (key.isReadable()){
                    receive.clear();
                    sc.read(receive);
                    System.out.println(new String(receive.array()));
                }else if (key.isWritable()){
                    receive.flip();
                    send.flip();
                    sc.write(send);
                }

            }

        }
    }
}
