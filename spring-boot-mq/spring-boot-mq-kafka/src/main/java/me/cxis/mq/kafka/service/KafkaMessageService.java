package me.cxis.mq.kafka.service;

import jakarta.annotation.Resource;
import me.cxis.mq.kafka.mq.KafkaTopic;
import me.cxis.mq.kafka.mq.producer.MessageSender;
import org.springframework.stereotype.Service;


@Service
public class KafkaMessageService {

    @Resource
    private MessageSender messageSender;

    public Boolean send(String message) {
        messageSender.send(KafkaTopic.KAFKA_TOPIC_TEST, message);
        return true;
    }
}
