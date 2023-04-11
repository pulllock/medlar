package me.cxis.mq.kafka.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

@Component
public class KafkaMessageSender implements MessageSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaMessageSender.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String topic, String message) {
        LOGGER.info("send message for topic: {}, message: {}", topic, message);
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, message);
    }
}
