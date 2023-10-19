package fun.pullock.websocket.netty.service;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import fun.pullock.websocket.netty.server.NettyConfig;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public void pushMessageToUser(String userId, String message) {
        NettyConfig.getUserChannels()
                .get(userId)
                .writeAndFlush(new TextWebSocketFrame(message));
    }

    public void pushMessageToAllUser(String message) {
        NettyConfig.getChannelGroup()
                .writeAndFlush(new TextWebSocketFrame(message));
    }
}
