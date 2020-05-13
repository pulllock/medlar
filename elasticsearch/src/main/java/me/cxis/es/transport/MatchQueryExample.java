package me.cxis.es.transport;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MatchQueryExample {
    public static String CLUSTER_NAME = "elasticsearch";

    public static String HOST = "localhost";

    public static int PORT = 9300;

    public static void main(String[] args) {
        TransportClient client = null;
        try {
            Settings settings = Settings
                .builder()
                .put("cluster.name", CLUSTER_NAME)
                .build();

            client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(HOST), PORT));

            QueryBuilder matchQuery = QueryBuilders
                .matchQuery("title", "python")
                .operator(Operator.AND);

            HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("title")
                .preTags("<span style='color:red'>")
                .preTags("</span>");

            SearchResponse searchResponse = client
                .prepareSearch("books")
                .setQuery(matchQuery)
                .highlighter(highlightBuilder)
                .setSize(100)
                .get();

            System.out.println(JSON.toJSONString(searchResponse));

            SearchHits hits = searchResponse.getHits();
            for (SearchHit hit : hits) {
                System.out.println(JSON.toJSONString(hit));

                Text[] texts = hit.getHighlightFields().get("title").getFragments();
                if (texts != null) {
                    for (Text text : texts) {
                        System.out.println(text);
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
