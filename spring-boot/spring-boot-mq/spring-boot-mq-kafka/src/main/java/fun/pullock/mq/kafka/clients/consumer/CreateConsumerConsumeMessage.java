package fun.pullock.mq.kafka.clients.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class CreateConsumerConsumeMessage {

    public static void main(String[] args) {
        // 消费者数量不能超过主题分区的数量，多余的消费者会被闲置

        Properties properties = new Properties();

        // bootstrap.servers指定broker的地址
        properties.put("bootstrap.servers", "localhost:9092");

        // group.id指定消费者属于哪个消费者群组
        properties.put("group.id", "TEST_CLIENTS_GROUP");

        // key.deserializer设置key的反序列化器
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // value.deserializer设置value的反序列化器
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // fetch.min.bytes指定了消费者从服务器获取记录的最小字节数。broker在收到消费者的数据请求时，如果可用数量小于fetch.min.bytes指定
        // 的大小，broker会等到有足够的可用数据时才把它返回给消费者
        properties.put("fetch.min.bytes", 1);

        // fetch.max.wait.ms指定broker的等待时间，如果没有足够的数据，broker会等待，这个配置可以设置等待的时间
        properties.put("fetch.max.wait.ms", 500);

        // max.partition.fetch.bytes指定了服务器从每个分区里返回给消费者的最大字节数
        properties.put("max.partition.fetch.bytes", 1 * 1024 * 1024);

        // session.timeout.ms指定消费者在被认为死亡之前可以与服务器断开连接的时间
        properties.put("session.timeout.ms", 45000);

        // auto.offset.reset指定了消费者在读取一个没有偏移量的分区或偏移量无效的情况下该如何处理，默认是latest
        // latest：偏移量无效的情况下，消费者从最新的记录开始读取数据，在消费者启动之后生成的记录
        // earliest：在偏移量无效的情况下，消费者将从起始位置读取分区的记录
        properties.put("auto.offset.reset", OffsetResetStrategy.LATEST.toString());

        // enable.auto.commit指定了消费者是否自动提交偏移量，默认是true，每隔5秒消费者会自动把从poll()方法接收到的最大偏移量提交上去，
        // 提交的时间间隔由auto.commit.interval.ms控制，默认是5s。
        // 可以将enable.auto.commit设置为false，由程序进行手动提交偏移量，使用commitSync()方法；还可以使用commitAsync()进行异步提交
        properties.put("enable.auto.commit", true);

        // partition.assignment.strategy分区分配策略，决定哪些分区应该被分配给哪个消费者
        // Range：该策略会把主题的若干个连续的分区分配给消费者
        // RoundRobin：该策略把主题的所有分区逐个分配给消费者
        properties.put("partition.assignment.strategy", Arrays.asList(RangeAssignor.class, CooperativeStickyAssignor.class));

        // client.id可以指定任意字符串，broker用它来标识从客户端发送过来的消息
        properties.put("client.id", "");

        // max.poll.records控制单次调用poll()方法能够返回的记录数量
        properties.put("max.poll.records", 500);

        // receive.buffer.bytes指定了TCP socket接受数据包的缓冲区大小，-1表示使用操作系统默认值
        properties.put("receive.buffer.bytes", 64 * 1024);

        // send.buffer.bytes指定了TCP socket发送数据包的缓存区大小，-1表示使用操作系统默认值
        properties.put("send.buffer.bytes", 128 * 1024);

        // 创建KafkaConsumer对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 订阅主题
        // 可以指定一个消费者再均衡监听器
        consumer.subscribe(Collections.singletonList("SYNC_FIRE_TOPIC"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                // 在再均衡开始之前和消费者停止读取消息之后被调用
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                // 在充分新分配分区之后和消费者开始读取消息之前被调用
            }
        });

        // 消息轮询
        try {
            while (true) {
                // poll()方法的参数是一个超时时间，控制poll()方法的阻塞时间，在消费者缓冲区里没有可用数据时会发生阻塞
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf(
                            "topic = %s, partition = %s, offset = %d, key = %s, value = %s%n",
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭消费者
            consumer.close();
        }
    }
}
