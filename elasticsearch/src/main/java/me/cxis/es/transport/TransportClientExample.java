package me.cxis.es.transport;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TransportClientExample {

    public static String CLUSTER_NAME = "elasticsearch";

    public static String HOST = "localhost";

    public static int PORT = 9300;

    public static void main(String[] args) {
        try {
            Settings settings = Settings
                .builder()
                .put("cluster.name", CLUSTER_NAME)
                .build();

            TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(HOST), PORT));

            GetResponse getResponse = client.prepareGet("books", "_doc", "1").get();
            System.out.println(JSON.toJSONString(getResponse));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
