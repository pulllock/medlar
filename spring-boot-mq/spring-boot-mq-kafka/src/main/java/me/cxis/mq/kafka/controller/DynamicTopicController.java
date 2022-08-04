package me.cxis.mq.kafka.controller;

import me.cxis.mq.kafka.mq.UserTopic;
import me.cxis.mq.kafka.mq.dynamic.DynamicMessageHandler;
import me.cxis.mq.kafka.mq.producer.MessageSender;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/dynamic/topic")
public class DynamicTopicController {

    Map<String, KafkaMessageListenerContainer<String, String>> containers = new ConcurrentHashMap<>();

    @Resource
    private DynamicMessageHandler dynamicMessageHandler;

    @Resource
    private MessageSender messageSender;

    @GetMapping("/consumer/register")
    public String registerConsumer(@RequestParam String topic) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        configs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, UserTopic.USER_GROUP);

        Deserializer<String> stringDeserializer = new StringDeserializer();
        DefaultKafkaConsumerFactory<String, String> factory = new DefaultKafkaConsumerFactory<>(configs, stringDeserializer, stringDeserializer);
        ContainerProperties props = new ContainerProperties(topic);
        // 每个topic对应一个MessageHandler，动态加载groovy脚本
        props.setMessageListener(dynamicMessageHandler);
        props.setGroupId(configs.get(ConsumerConfig.GROUP_ID_CONFIG).toString());
        // props.setAckMode(ContainerProperties.AckMode.MANUAL);
        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(factory, props);
        container.start();

        containers.putIfAbsent(topic, container);

        return "success";
    }

    @GetMapping("/consumer/pause")
    public String pauseConsumer(@RequestParam String topic) {
        KafkaMessageListenerContainer<String, String> container = containers.get(topic);
        if (container == null) {
            return "success";
        }

        if (container.isRunning()) {
            container.pause();
        }

        return "success";
    }

    @GetMapping("/consumer/resume")
    public String resumeConsumer(@RequestParam String topic) {
        KafkaMessageListenerContainer<String, String> container = containers.get(topic);
        if (container == null) {
            return "not exist";
        }

        if (container.isContainerPaused()) {
            container.resume();
        }

        return "success";
    }

    @GetMapping("/producer/send")
    public String sendMessage(@RequestParam String topic, @RequestParam String msg) {
        messageSender.send(topic, msg);
        return "success";
    }
}
