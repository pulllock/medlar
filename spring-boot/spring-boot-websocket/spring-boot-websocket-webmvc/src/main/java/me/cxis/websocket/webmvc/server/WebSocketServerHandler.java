package me.cxis.websocket.webmvc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketServerHandler implements WebSocketHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

    /**
     * 存放所有连接的客户端
     */
    private static Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();

    /**
     * 建立链接
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("新的客户端连接：{}", session.getId());
        clients.put(session.getId(), session);
    }

    /**
     * 接收消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        LOGGER.info("收到客户端发来的消息，客户端ID：{}，消息：{}", session.getId(), message);
        sendMessageToAllUser((String) message.getPayload(), session);
    }

    /**
     * 异常处理
     * @param session
     * @param e
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
        LOGGER.error("出现异常，客户端ID：{}，异常：", session.getId(), e);
    }

    /**
     * 关闭连接
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LOGGER.info("客户端断开连接：{}, 原因：{}", session.getId(), closeStatus.getReason());
        clients.remove(session.getId());
    }

    /**
     * 是否支持接收不完整的消息
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void sendMessageToAllUser(String message, WebSocketSession fromSession) {
        message = String.format("这是来自客户端：%s的消息，内容为：%s", fromSession.getId(), message);
        for (Map.Entry<String, WebSocketSession> sessionEntry : clients.entrySet()) {
            try {
                sessionEntry.getValue().sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
