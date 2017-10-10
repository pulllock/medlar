package me.cxis.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by cx on 8/29/16.
 *
 * http://blog.csdn.net/chenxuegui1234/article/details/17979725
 *
 * http://www.blogjava.net/heyang/archive/2011/01/03/342193.html
 *
 * http://www.importnew.com/13602.html
 *
 */
public class MyServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        //接收和发送数据缓冲区
        ByteBuffer send = ByteBuffer.allocate(1024);
        ByteBuffer receive = ByteBuffer.allocate(1024);

        //打开服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //服务器配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //检测与此通道关联的服务器套接字
        ServerSocket serverSocket = serverSocketChannel.socket();
        //绑定服务
        serverSocket.bind(new InetSocketAddress(port));
        //通过open()方法找到Selector
        Selector selector = Selector.open();
        //注册到Selector 等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("NioServer======server started at:" + port);

        //向发送缓冲区加入数据
        send.put("data come from server".getBytes());

        while(true){
            //选择一组键,并且相应的通道已经打开
            selector.select();
            //返回此选择器的已选择键集
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //手动remove掉
                iterator.remove();
                SocketChannel client = null;

                //测试此键的通道是否已准备好接受新的套接字连接
                if(selectionKey.isAcceptable()){
                    //返回为之创建此键的通道
                    ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                    //此方法返回的套接字通道处于阻塞模式
                    client = server.accept();
                    //配置为非阻塞
                    client.configureBlocking(false);
                    //注册到selector,等待连接
                    client.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }else if(selectionKey.isReadable()){
                    //返回为之创建此键的通道
                    client = (SocketChannel)selectionKey.channel();
                    //将缓冲区清空以备下次读取
                    receive.clear();
                    //读取服务器发送来的数据到缓冲区
                    client.read(receive);
                    System.out.println(new String(receive.array()));
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                }else if (selectionKey.isWritable()) {
                    // 将缓冲区清空以备下次写入
                    send.flip();
                    // 返回为之创建此键的通道。
                    client = (SocketChannel) selectionKey.channel();
                    // 输出到通道
                    client.write(send);

                    selectionKey.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }

}
