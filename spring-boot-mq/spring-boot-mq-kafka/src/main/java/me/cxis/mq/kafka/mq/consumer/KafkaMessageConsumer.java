package me.cxis.mq.kafka.mq.consumer;

import me.cxis.mq.kafka.mq.KafkaTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = KafkaTopic.KAFKA_TOPIC_TEST)
    public void consume(String message) {
        LOGGER.info("received message: {}", message);
    }
}
