package fun.pullock.mq.rocketmq;

import fun.pullock.mq.rocketmq.consumer.UserRegisterConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQConfig {

    private String nameServer = "xxxx.xxxx.xxxx";

    private String consumerGroup = "xxxx_yyyy_group";

    @Bean(initMethod = "start", destroyMethod = "stop")
    public UserRegisterConsumer userRegisterConsumer() {
        UserRegisterConsumer consumer = new UserRegisterConsumer();
        consumer.setNameServer(nameServer);
        consumer.setConsumerGroup(consumerGroup);
        consumer.setConsumeThreadMin(5);
        consumer.setConsumeThreadMax(10);
        return consumer;
    }
}
