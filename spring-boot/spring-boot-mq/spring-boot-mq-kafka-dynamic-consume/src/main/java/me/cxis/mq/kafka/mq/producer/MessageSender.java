package me.cxis.mq.kafka.mq.producer;

public interface MessageSender {

    void send(String topic, String message);
}
