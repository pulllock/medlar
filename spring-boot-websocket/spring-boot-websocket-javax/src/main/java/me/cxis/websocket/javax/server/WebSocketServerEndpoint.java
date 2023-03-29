package me.cxis.websocket.javax.server;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/api/message/websocket")
public class WebSocketServerEndpoint {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketServerEndpoint.class);

    /**
     * 存放所有连接的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    /**
     * 建立连接
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        LOGGER.info("新的客户端连接：{}, config: {}", session.getId(), config);
        clients.put(session.getId(), session);
    }

    /**
     * 连接关闭
     * @param session
     * @param reason
     */
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        LOGGER.info("客户端断开连接：{}, 原因：{}", session.getId(), reason.getReasonPhrase());
        clients.remove(session.getId());
    }

    /**
     * 接收文本消息
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        LOGGER.info("收到客户端发来的消息，客户端ID：{}，消息：{}", session.getId(), message);
        sendMessageToAllUser(message, session);
    }

    /**
     * 接收pong信息
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, PongMessage message) {
        LOGGER.info("收到客户端发来的Pong消息，客户端ID：{}，消息：{}", session.getId(), message);
    }

    /**
     * 接收字节消息
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, ByteBuffer message) {
        LOGGER.info("收到客户端发来的字节消息，客户端ID：{}，消息：{}", session.getId(), message);
    }

    /**
     * 异常处理
     * @param session
     * @param e
     */
    @OnError
    public void onError(Session session, Throwable e) {
        LOGGER.error("出现异常，客户端ID：{}，异常：", session.getId(), e);
    }

    private void sendMessageToAllUser(String message, Session fromSession) {
        message = String.format("这是来自客户端：%s的消息，内容为：%s", fromSession.getId(), message);
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }
}
