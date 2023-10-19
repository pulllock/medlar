package fun.pullock.websocket.netty.cluster.redis.server;

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
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NettyServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

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
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                // HTTP协议解析，用于握手阶段
                                .addLast(new HttpServerCodec())
                                // HTTP协议解析，用于握手阶段，消息聚合器，将消息聚合成FullHttpRequest
                                .addLast(new HttpObjectAggregator(65536))
                                // 支持大文件传输
                                .addLast(new ChunkedWriteHandler())
                                // WebSocket数据压缩
                                .addLast(new WebSocketServerCompressionHandler())
                                // WebSocket握手、控制帧处理，会处理握手、Close、Ping、Pong帧等WebSocket协议底层，并将Text和Binary数据帧传递给pipeline中下一个handler中
                                .addLast(new WebSocketServerProtocolHandler(path, "WebSocket", true, 65536 * 10))
                                // 自定义业务处理器
                                .addLast(webSocketServerHandler);
                    }
                });

        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        LOGGER.info("Netty WebSocket server started on port: {}, path: {}", port, path);
        channelFuture.channel().closeFuture().sync();
        LOGGER.info("Netty WebSocket server closed on port: {}, path: {}", port, path);
    }

    @PreDestroy
    public void destroy() throws InterruptedException {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().sync();
        }

        if (workGroup != null) {
            workGroup.shutdownGracefully().sync();
        }
    }
}
