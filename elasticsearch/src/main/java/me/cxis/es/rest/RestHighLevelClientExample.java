package me.cxis.es.rest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class RestHighLevelClientExample {

    public static String HOST = "localhost";

    public static int PORT = 9200;

    public static void main(String[] args) {
        RestHighLevelClient client = null;
        try {
            client = new RestHighLevelClient(
                RestClient.builder(
                    new HttpHost(HOST, PORT, "http")
                )
            );

            GetRequest getRequest = new GetRequest("books", "1");
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println(getResponse.getSourceAsString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
