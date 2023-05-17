package me.cxis.es.config

import me.cxis.es.AbstractIntegrationSpec
import me.cxis.es.config.ESClient

import javax.annotation.Resource

class ESClientIntegrationSpec extends AbstractIntegrationSpec {

    @Resource
    ESClient esClient

    def "验证索引存在" () {
        given: "存在的索引名字"
        def index = "forms_sub_health"

        when: "调用indexExists查询索引是否存在"
        def result = esClient.indexExists(index)

        then: "验证查询结果"
        println(result)
        result
    }
}
