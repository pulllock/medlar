package me.cxis.es.service;

import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import me.cxis.es.config.ESClient;
import me.cxis.es.model.SubHealth;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("formsSearchService")
public class FormsSearchService {

    private final static String INDEX_NAME = "forms_sub_health";

    @Resource
    private ESClient esClient;

    public List<SubHealth> searchByAge(int age) throws IOException {
        SearchResponse<SubHealth> response = esClient.getClient().search(
                s -> s.index(INDEX_NAME)
                        .query(
                                q -> q.term(t -> t.field("age")
                                        .value(v -> v.longValue(age)))
                        ),
                SubHealth.class);

        List<SubHealth> result = new ArrayList<>();
        for (Hit<SubHealth> hit : response.hits().hits()) {
            result.add(hit.source());
        }

        return result;
    }

    public SubHealth getById(Long id) throws IOException {
        GetResponse<SubHealth> response  = esClient.getClient().get(
                g -> g.index(INDEX_NAME)
                        .id(String.valueOf(id)),
                SubHealth.class
        );

        if (response.found()) {
            return response.source();
        }

        return null;
    }

    public List<SubHealth> searchByGteAgeAndGender(int age, String gender) throws IOException {
        Query ageQuery = RangeQuery.of(m -> m.field("age").gte(JsonData.of(age)))._toQuery();
        Query genderQuery = TermQuery.of(m -> m.field("gender").value(gender))._toQuery();

        SearchResponse<SubHealth> response = esClient.getClient().search(
                s -> s.index(INDEX_NAME)
                        .query(q -> q.bool(
                                b -> b.must(ageQuery)
                                        .must(genderQuery)
                        )),
                SubHealth.class
        );

        List<SubHealth> result = new ArrayList<>();
        for (Hit<SubHealth> hit : response.hits().hits()) {
            result.add(hit.source());
        }

        return result;
    }

    public Map<String, Long> groupByGender() throws IOException {
        String aggKey = "group_by_gender";
        SearchResponse<Void> response = esClient.getClient().search(
                s -> s.index(INDEX_NAME)
                        .size(0)
                        .aggregations(aggKey, a -> a.terms(t -> t.field("gender"))),
                Void.class);

        List<StringTermsBucket> buckets = response.aggregations().get(aggKey).sterms().buckets().array();
        Map<String, Long> result = new HashMap<>();
        for (StringTermsBucket bucket : buckets) {
            result.put(bucket.key(), bucket.docCount());
        }
        return result;
    }
}
