package me.cxis.es.transport;

import com.alibaba.fastjson.JSON;
import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import org.elasticsearch.action.admin.indices.close.CloseIndexResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IndicesAdminClientExample {

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

            IndicesAdminClient adminClient = client.admin().indices();

            // 判断索引是否存在
            IndicesExistsResponse existsResponse = adminClient.prepareExists("books").get();
            System.out.println(JSON.toJSONString(existsResponse));

            existsResponse = adminClient.prepareExists("bookksss").get();
            System.out.println(JSON.toJSONString(existsResponse));

            // 判断type是否存在
            TypesExistsResponse typesExistsResponse = adminClient.prepareTypesExists("books").setTypes("text", "date").get();
            System.out.println(JSON.toJSONString(typesExistsResponse));

            // 创建索引
            CreateIndexResponse createIndexResponse = adminClient.prepareCreate("books2").get();
            System.out.println(JSON.toJSONString(createIndexResponse));

            // 创建并设置Settings
            createIndexResponse = adminClient
                .prepareCreate("books3")
                .setSettings(
                    Settings.builder()
                        .put("index.number_of_shards",3)
                        .put("index.number_of_replicas", 1)
                    ).get();
            System.out.println(JSON.toJSONString(createIndexResponse));

            // 更新副本
            AcknowledgedResponse updateSettingsResponse = adminClient.prepareUpdateSettings("books3")
                .setSettings(
                    Settings.builder()
                        .put("index.number_of_replicas", 0)
                )
                .get();
            System.out.println(JSON.toJSONString(updateSettingsResponse));

            // 获取settings
            GetSettingsResponse getSettingsResponse = adminClient
                .prepareGetSettings("books3", "books2")
                .get();
            for (ObjectObjectCursor<String, Settings> indexToSetting : getSettingsResponse.getIndexToSettings()) {
                System.out.println(indexToSetting.toString());
            }

            // 设置mapping
           /* AcknowledgedResponse setMappingResponse = adminClient.preparePutMapping("books3")
                .setType("book3")
                .setSource("{\n" +
                    "  \"properties\": {\n" +
                    "    \"title\": {\n" +
                    "      \"type\": \"text\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}")
                .get();
            System.out.println(JSON.toJSONString(setMappingResponse));*/

            // 获取mapping
            GetMappingsResponse getMappingsResponse = adminClient
                .prepareGetMappings("books3")
                .get();

            System.out.println(JSON.toJSONString(getMappingsResponse));

            // 删除索引
            AcknowledgedResponse deleteResponse = adminClient.prepareDelete("books2").get();
            System.out.println(JSON.toJSONString(deleteResponse));

            deleteResponse = adminClient.prepareDelete("books3").get();
            System.out.println(JSON.toJSONString(deleteResponse));

            // 刷新
            RefreshResponse refreshResponse = adminClient.prepareRefresh().get();
            System.out.println(JSON.toJSONString(refreshResponse));

            // 关闭索引
            CloseIndexResponse closeIndexResponse = adminClient.prepareClose("books").get();
            System.out.println(JSON.toJSONString(closeIndexResponse));

            // 打开索引
            OpenIndexResponse openIndexResponse = adminClient.prepareOpen("books").get();
            System.out.println(JSON.toJSONString(openIndexResponse));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
