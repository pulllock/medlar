package me.cxis.mq.rocketmq.consumer;

import static me.cxis.mq.rocketmq.enums.UCTopic.USER_REGISTERED;

public class UserRegisterConsumer extends BaseConsumerConcurrently {

    @Override
    protected String setTopic() {
        return USER_REGISTERED.getTopic();
    }

    @Override
    protected String setTag() {
        return USER_REGISTERED.getTag();
    }

    @Override
    protected boolean doConsumeMessage(String message) {
        try {
            System.out.println(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
