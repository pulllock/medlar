package me.cxis.mq.rocketmq.producer;

import me.cxis.mq.rocketmq.enums.UCTopic;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

import static me.cxis.mq.rocketmq.constants.Constants.NAME_SERVER_ADDR;

public class AsyncProducer {

    private static final String GROUP_NAME = "user_center_mq_group";

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(GROUP_NAME);

        producer.setNamesrvAddr(NAME_SERVER_ADDR);

        producer.start();

        producer.setRetryTimesWhenSendAsyncFailed(0);

        for (int i = 0; i < 100; i++) {
            String userId = "10000" + i;

            final int index = i;

            Message message = new Message(
                UCTopic.USER_REGISTERED.getTopic(),
                UCTopic.USER_REGISTERED.getTag(),
                userId.getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(index + "send Ok, result:" + sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println(index + "send Exception!");
                    throwable.printStackTrace();
                }
            });
        }

        producer.shutdown();
    }
}
