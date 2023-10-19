package fun.pullock.mq.kafka.service;

import fun.pullock.mq.kafka.mq.producer.MessageSender;
import jakarta.annotation.Resource;
import fun.pullock.mq.kafka.mq.KafkaTopic;
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
