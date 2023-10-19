package fun.pullock.websocket.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class NettyServer {

    /**
     * 端口
     */
    @Value("${netty.websocket.port:8989}")
    private int port;

    /**
     * 路径
     */
    @Value("${netty.websocket.path:/websocket}")
    private String path;

    @Resource
    private WebSocketServerHandler webSocketServerHandler;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workGroup;

    @PostConstruct
    public void init() {
        new Thread(
                () -> {
                    try {
                        start();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).start();
    }

    private void start() throws InterruptedException {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                // NIO
                .channel(NioServerSocketChannel.class)
                // 监听端口
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                // http编解码器
                                .addLast(new HttpServerCodec())
                                .addLast(new ObjectEncoder())
                                .addLast(new ChunkedWriteHandler())
                                // 分段聚合
                                .addLast(new HttpObjectAggregator(8192))
                                // websocket协议
                                .addLast(new WebSocketServerProtocolHandler(path, "WebSocket", true, 65536 * 10))
                                // 自定义业务处理器
                                .addLast(webSocketServerHandler);
                    }
                });

        ChannelFuture channelFuture = bootstrap.bind().sync();
        channelFuture.channel().closeFuture().sync();
    }

    @PreDestroy
    public void destroy() throws InterruptedException {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().sync();
        }
    }
}
