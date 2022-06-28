package me.cxis.mq.kafka.mq.consumer;

import me.cxis.mq.kafka.mq.UserTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserRegisterConsumer.class);

    @KafkaListener(topics = UserTopic.USER_REGISTER, groupId = UserTopic.USER_GROUP)
    public void consume(String message) {
        LOGGER.info("received message: {}", message);
    }
}
