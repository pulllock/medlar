package me.cxis.mq.kafka.service;

import me.cxis.mq.kafka.mq.UserTopic;
import me.cxis.mq.kafka.mq.producer.MessageSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private MessageSender messageSender;

    public Boolean register(String name) {
        messageSender.send(UserTopic.USER_REGISTER, name);
        return true;
    }
}
