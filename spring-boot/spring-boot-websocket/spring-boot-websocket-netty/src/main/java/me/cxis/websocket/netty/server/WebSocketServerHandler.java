package me.cxis.websocket.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        LOGGER.info("收到消息，channel: {}, message: {}", channelHandlerContext.channel().id().asLongText(), textWebSocketFrame.text());

        // 获取userId
        String userId = "mock_user_id";

        // 放到userChannels中
        NettyConfig.getUserChannels().put(userId, channelHandlerContext.channel());

        // 放到channel的自定义属性中
        channelHandlerContext.attr(AttributeKey.valueOf("userId")).setIfAbsent(userId);

        // 回复消息
        channelHandlerContext.writeAndFlush(new TextWebSocketFrame("收到消息"));
        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.info("exception caught, channel: {}, cause: ", ctx.channel().id().asLongText(), cause);
        // 从channelGroup中移除
        NettyConfig.getChannelGroup().remove(ctx.channel());

        // 从userChannels中删除
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        NettyConfig.getUserChannels().remove(userId);

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
        NettyConfig.getUserChannels().remove(userId);

        ctx.close();
    }
}
