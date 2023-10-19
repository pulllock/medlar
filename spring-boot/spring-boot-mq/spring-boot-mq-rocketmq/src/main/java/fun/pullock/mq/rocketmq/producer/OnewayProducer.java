package fun.pullock.mq.rocketmq.producer;

import fun.pullock.mq.rocketmq.constants.Constants;
import fun.pullock.mq.rocketmq.enums.UCTopic;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class OnewayProducer {

    private static final String GROUP_NAME = "user_center_mq_group";

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(GROUP_NAME);

        producer.setNamesrvAddr(Constants.NAME_SERVER_ADDR);

        producer.start();

        for (int i = 0; i < 100; i++) {
            String userId = "10000" + i;
            Message message = new Message(
                UCTopic.USER_REGISTERED.getTopic(),
                UCTopic.USER_REGISTERED.getTag(),
                userId.getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            producer.sendOneway(message);

        }

        producer.shutdown();
    }
}
