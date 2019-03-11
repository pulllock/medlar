package me.cxis.rocketmq.consumer;

import me.cxis.rocketmq.enums.UCTopic;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {

    private static final String GROUP_NAME = "user_center_mq_group_consumer";

    private static final String NAME_SERVER_ADDR = "192.168.56.101:9876";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(GROUP_NAME);

        consumer.setNamesrvAddr(NAME_SERVER_ADDR);

        consumer.subscribe(UCTopic.USER_REGISTERED.getTopic(), UCTopic.USER_REGISTERED.getTag());

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.println("started!");
    }
}
