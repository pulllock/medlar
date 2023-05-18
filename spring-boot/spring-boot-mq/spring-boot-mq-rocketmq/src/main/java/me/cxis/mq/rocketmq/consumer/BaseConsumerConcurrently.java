package me.cxis.mq.rocketmq.consumer;

import me.cxis.mq.rocketmq.json.Json;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

import static org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
import static org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus.RECONSUME_LATER;

public abstract class BaseConsumerConcurrently implements MessageListenerConcurrently {

    protected DefaultMQPushConsumer consumer;

    protected String consumerGroup;

    protected String nameServer;

    protected int consumeThreadMin = 5;

    protected int consumeThreadMax = 8;

    public void start() {
        consumer = new DefaultMQPushConsumer(getConsumerGroup());
        consumer.setNamesrvAddr(nameServer);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setInstanceName(getInstanceName());
        try {
            consumer.subscribe(setTopic(), setTag());
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        consumer.registerMessageListener(this);
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    protected abstract String setTopic();

    protected abstract String setTag();

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
        try {
            if (messages == null || messages.isEmpty()) {
                return CONSUME_SUCCESS;
            }

            boolean hasFail = false;
            for (MessageExt message : messages) {
                // 假设消息都是json
                if (!doConsumeMessage(Json.toJsonString(message))) {
                    hasFail = true;
                }
            }

            if (hasFail) {
                return RECONSUME_LATER;
            }

            return CONSUME_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return RECONSUME_LATER;
        }
    }

    protected abstract boolean doConsumeMessage(String message);


    public void stop() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    /**
     * 同一消费组订阅的topic、topic中的tag必须保持一致，否则会出现消息丢失情况
     * @return
     */
    private String getConsumerGroup() {
        return String.format("%s_%s_%s", consumerGroup, setTopic(), setTag());
    }

    private String getInstanceName() {
        // TODO 可使用项目名作为前缀
        String projectName = "xxxxx";
        return String.format("%s_%s_%s", projectName, setTopic(), setTag());
    }
}
