package fun.pullock.mq.kafka.clients.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class CreateProducerSendMessage {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // bootstrap.servers指定broker的地址，格式：host:port。不需要包含所有的broker地址，生产者会从给定的broker里查找其他的broker的信息。
        // 建议至少提供两个broker信息。
        properties.put("bootstrap.servers", "localhost:9092");

        // key.serializer设置key的序列化器
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // value.serializer设置value的序列化器
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // acks指定必须要有多少个分区副本收到消息，生产者才会认为消息写入时成功的
        // acks = 0：生产者在成功写入消息之前不会等待任何来自服务器的响应
        // acks = 1：只要集群leader节点收到消息，生产者就会收到一个来自服务器的响应
        // acks = all：只有当所有参与复制的节点全部都收到消息时，生产者才会收到一个来自服务器的响应
        properties.put("acks", "all");

        // buffer.memory用来设置生产者内存缓冲区的大小，用来缓冲要发送到服务器的消息
        properties.put("buffer.memory", 32 * 1024 * 1024L);

        // compression.type指定消息被发送给broker之前使用哪一种压缩算法进行压缩
        // none：不压缩
        // snappy：占用较少CPU，提供较好的性能和相当客观的压缩比
        // gzip：会占用较多的CPU，提供更高的压缩比
        // lz4：
        properties.put("compression.type", "none");

        // retries决定了生产者可以重发消息的次数
        // 默认情况下生产者在每次重试之间等待100ms，可以通过retry.backoff.ms来改变间隔
        properties.put("retries", 2);

        // batch.size指定一个批次可以使用的内存大小（字节数），
        // 多个消息需要被发送到同一个分区时，生产者会把它们放到同一个批次里
        properties.put("batch.size", 16384);

        // linger.ms指定了生产者在发送批次之前等待更多消息加入批次的时间，会在批次填满或者linger.ms达到上限时把批次发送出去
        properties.put("linger.ms", 0);

        // client.id服务器用来识别消息的来源
        properties.put("client.id", "");

        // max.in.flight.requests.per.connection指定了生产者在收到服务器响应之前可以发送多少个消息，
        // 设为1可以保证消息是按照发送的顺序写入服务器的
        properties.put("max.in.flight.requests.per.connection", 5);

        // timeout.ms指定了broker等待同步副本返回消息确认的时间
        properties.put("timeout.ms", 1);

        // request.timeout.ms指定了生产者在发送数据时等待服务器返回响应的时间
        properties.put("request.timeout.ms", 30 * 1000);

        // metadata.fetch.timeout.ms指定了生产者在获取元数据时等待服务器返回响应的时间
        properties.put("metadata.fetch.timeout", 1);

        // max.block.ms指定了在调用send()方法或使用partitionsFor()方法获取元数据时生产者的阻塞时间
        properties.put("max.block.ms", 60 * 1000);

        // max.request.size用于控制生产者发送的请求大小，可以指能发送的单个消息的最大值，也可以指单个请求里所有消息总的大小
        properties.put("max.request.size", 1024 * 1024);

        // receive.buffer.bytes指定了TCP socket接受数据包的缓冲区大小，-1表示使用操作系统默认值
        properties.put("receive.buffer.bytes", 32 * 1024);

        // send.buffer.bytes指定了TCP socket发送数据包的缓存区大小，-1表示使用操作系统默认值
        properties.put("send.buffer.bytes", 128 * 1024);

        // 实例化生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 发送消息，有三种方式：
        // 1. 发送并忘记（fire-and-forget）：将消息发给服务器，不关心是否正常送达，可能会丢失一些消息
        // 2. 同步发送：使用send()方法发送消息，会返回一个Future对象，调用get()方法进行等待，可以知道消息是否发送成功
        // 3. 异步发送：调用send()方法发送消息，并指定一个回调方法，服务器在返回响应时调用该回调方法

        // ProducerRecord对象包含：目标主题、键、值
        // 键可以作为消息的附加信息、也可以用来决定消息被写到主题的哪个分区，有相同键的消息将会被写到同一个分区
        // 默认分区器使用轮询算法（Round Robin）将消息均衡的分布到各个分区上
        ProducerRecord<String, String> record = null;

        // 发送并忘记
        record = new ProducerRecord<>("FIRE_AND_FORGET_TOPIC", "FIRE_AND_FORGET_KEY", "fire and forget value");
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 同步发送
        record = new ProducerRecord<>("SYNC_FIRE_TOPIC", "SYNC_FIRE_KEY", "sync fire");
        try {
            RecordMetadata recordMetadata = producer.send(record).get();
            System.out.println("sync fire result: " + recordMetadata);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 异步发送
        record = new ProducerRecord<>("ASYNC_FIRE_TOPIC", "ASYNC_FIRE_KEY", "async fire");
        producer.send(record, (metadata, exception) -> {
            System.out.println("async fire result: " + metadata + ", exception: " + exception);
        });
    }
}
