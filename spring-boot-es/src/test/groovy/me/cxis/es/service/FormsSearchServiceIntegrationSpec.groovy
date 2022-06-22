package me.cxis.es.service

import me.cxis.es.AbstractIntegrationSpec

import javax.annotation.Resource

class FormsSearchServiceIntegrationSpec extends AbstractIntegrationSpec {

    @Resource
    FormsSearchService formsSearchService

    def "测试search方法"() {
        expect:
        def result = formsSearchService.searchByAge(0)
        println(result)
        println(result.size())
        result
    }

    def "测试gtById方法" () {
        expect:
        def result = formsSearchService.getById(1)
        println(result)
        result
    }

    def "测试searchByGteAgeAndGender方法" () {
        expect:
        def result = formsSearchService.searchByGteAgeAndGender(18, "male")
        println(result)
        println(result.size())
        result
    }

    def "测试groupByGender方法" () {
        expect:
        def result = formsSearchService.groupByGender();
        println(result)
        result
    }
}
