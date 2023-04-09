package me.cxis.websocket.netty.cluster.redis.listener;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import me.cxis.websocket.netty.cluster.redis.server.NettyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class WebSocketMessageListener implements MessageListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketMessageListener.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String messageValue = new String(message.getBody());
        LOGGER.info("receive message form redis, message: {}", message);
        NettyConfig.getChannelGroup()
                .writeAndFlush(new TextWebSocketFrame(messageValue));
    }
}
