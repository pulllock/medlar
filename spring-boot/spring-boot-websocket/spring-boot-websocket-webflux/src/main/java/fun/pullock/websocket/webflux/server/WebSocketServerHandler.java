package fun.pullock.websocket.webflux.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class WebSocketServerHandler implements WebSocketHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        // TODO
        return session.send(
                session.receive()
                        .map(msg -> {
                            String message = String.format("这是来自客户端：%s的消息，内容为：%s", session.getId(), msg.getPayloadAsText());
                            return session.textMessage(message);
                        })
        );
    }

}
