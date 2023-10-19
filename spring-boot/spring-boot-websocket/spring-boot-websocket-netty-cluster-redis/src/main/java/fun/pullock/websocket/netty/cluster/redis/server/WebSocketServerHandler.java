package fun.pullock.websocket.netty.cluster.redis.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@ChannelHandler.Sharable
public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        LOGGER.info("收到事件，context: {}, event: {}", ctx, evt);
        // HandshakeComplete：握手成功事件，可以处理用户认证等
        if (evt instanceof HandshakeComplete) {
            HandshakeComplete handshakeComplete = (HandshakeComplete) evt;
            LOGGER.info(
                    "收到HandshakeComplete类型事件，channel: {}, event: {}, requestHeaders: {}, requestUri: {}, selectedSubprotocol: {}",
                    ctx.channel().id().asLongText(),
                    handshakeComplete,
                    handshakeComplete.requestHeaders(),
                    handshakeComplete.requestUri(),
                    handshakeComplete.selectedSubprotocol()
            );
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, WebSocketFrame webSocketFrame) throws Exception {
        LOGGER.info("收到消息，context: {}, frame: {}", context, webSocketFrame.content().toString(StandardCharsets.UTF_8));
        // TextWebSocketFrame：文本数据
        if (webSocketFrame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) webSocketFrame;
            LOGGER.info(
                    "收到TextWebSocketFrame类型消息，channel: {}, message: {}",
                    context.channel().id().asLongText(),
                    textWebSocketFrame.text()
            );

            // 获取userId
            String userId = "mock_user_id";

            // 放到userChannels中
            NettyConfig.getUserChannels().put(userId, context.channel());

            // 放到channel的自定义属性中
            context.attr(AttributeKey.valueOf("userId")).setIfAbsent(userId);

            // 回复消息
            context.writeAndFlush(new TextWebSocketFrame("已收到TextWebSocketFrame类型消息"));
        }

        // BinaryWebSocketFrame：二进制数据
        else if (webSocketFrame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) webSocketFrame;
            LOGGER.info(
                    "收到BinaryWebSocketFrame类型消息，channel: {}, message: {}",
                    context.channel().id().asLongText(),
                    binaryWebSocketFrame.content().toString(StandardCharsets.UTF_8)
            );
        }

        // ContinuationWebSocketFrame：表示消息中多于一个Frame，包含了属于上一个BinaryWebSocketFrame或TextWebSocketFrame的文本数据或二进制数据
        else if (webSocketFrame instanceof ContinuationWebSocketFrame) {
            ContinuationWebSocketFrame continuationWebSocketFrame = (ContinuationWebSocketFrame) webSocketFrame;
            LOGGER.info(
                    "收到ContinuationWebSocketFrame类型消息，channel: {}, message: {}",
                    context.channel().id().asLongText(),
                    continuationWebSocketFrame.content().toString(StandardCharsets.UTF_8)
            );
        }

        // PingWebSocketFrame：Ping数据
        else if (webSocketFrame instanceof PingWebSocketFrame) {
            PingWebSocketFrame pingWebSocketFrame = (PingWebSocketFrame) webSocketFrame;
            LOGGER.info(
                    "收到PingWebSocketFrame类型消息，channel: {}, message: {}",
                    context.channel().id().asLongText(),
                    pingWebSocketFrame.content().toString(StandardCharsets.UTF_8)
            );
        }

        // PongWebSocketFrame：对PingWebSocketFrame的响应
        else if (webSocketFrame instanceof PongWebSocketFrame) {
            PongWebSocketFrame pongWebSocketFrame = (PongWebSocketFrame) webSocketFrame;
            LOGGER.info(
                    "收到PongWebSocketFrame类型消息，channel: {}, message: {}",
                    context.channel().id().asLongText(),
                    pongWebSocketFrame.content().toString(StandardCharsets.UTF_8)
            );
        }

        // CloseWebSocketFrame：CLOSE请求，包含一个关闭状态码和原因
        else if (webSocketFrame instanceof CloseWebSocketFrame) {
            CloseWebSocketFrame closeWebSocketFrame = (CloseWebSocketFrame) webSocketFrame;
            LOGGER.info(
                    "收到CloseWebSocketFrame类型消息，channel: {}, message: {}",
                    context.channel().id().asLongText(),
                    closeWebSocketFrame.content().toString(StandardCharsets.UTF_8)
            );
        }

        else {
            LOGGER.info(
                    "收到其他类型消息，channel: {}, message: {}",
                    context.channel().id().asLongText(),
                    webSocketFrame.content().toString(StandardCharsets.UTF_8)
            );
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.info("exception caught, channel: {}, cause: ", ctx.channel().id().asLongText(), cause);
        // 从channelGroup中移除
        NettyConfig.getChannelGroup().remove(ctx.channel());

        // 从userChannels中删除
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        if (userId != null && userId.length() > 0) {
            NettyConfig.getUserChannels().remove(userId);
        }

        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("handler added, channel: {}", ctx.channel().id().asLongText());
        // 添加到channelGroup中
        NettyConfig.getChannelGroup().add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("handler removed, channel: {}", ctx.channel().id().asLongText());
        // 从channelGroup中移除
        NettyConfig.getChannelGroup().remove(ctx.channel());

        // 从userChannels中删除
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        if (userId != null && userId.length() > 0) {
            NettyConfig.getUserChannels().remove(userId);
        }

        ctx.close();
    }
}
